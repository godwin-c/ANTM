/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.services.ImageDownloadService;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.maps.providers.GoogleMapsProvider;
import com.codename1.processing.Result;
import generated.StateMachineBase;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mtech.antm.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Agada Godwin C.
 */
public class StateMachine extends StateMachineBase {

    AppUsers appUser;
    Contestants contestant;
    News news;
    ScoutingVenue scoutingVenue;
    Hashtable<String, String> applicant;
    Hashtable<String, String> contestantToDisplay;
    Vector<Hashtable> contestantsFetched;
    Vector<Hashtable> galleryImages;
    Vector<Hashtable> scoutDates;
    Vector<Hashtable> allNews;
    Vector<Hashtable> allScoutingLocations;
    //Vector<Hashtable> ctryCodes;
    Hashtable<String, String> returns;
    String imagePath;
    String imageString;
    String status;
    String regResponse;
    byte[] photoByte;
    double latt;
    double longs;
    EncodedImage photo;
    Result result;
    String status2;
    InputStream countryCodeStream;
    String nextURL;
    String appId = "GiCVsxB2ZrOcUaTMXHl1JXIiBYKbmSgB6fpEdrVn";
    String restApiKey = "SUk9g42zC9ZRHJYIFbisdKM7FRGckKV33gtq3QNh";
    String myKey = "AIzaSyB7t94-6U-G2PTWC_czQ-LwsNMbUaTqmO4";

    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    @Override
    protected void initVars(Resources res) {
    }

    public void registerAppUser(String username, String email, String country, String number) {

        Hashtable data = new Hashtable();
        data.put("name", username);
        data.put("country", country);
        data.put("email", email);
        data.put("phone", number);

        final String json = Result.fromContent(data).toString();

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(json.getBytes("UTF-8"));
            }
            //   **************** Get the status of the connection        

//            @Override
//            protected void readHeaders(Object connection) throws IOException {
//
//                status = getHeader(connection, null);
//                System.out.println("The status of the connection: " + status);
//            }
//            //*****************
            @Override
            protected void readResponse(InputStream input) throws IOException {


                status = String.valueOf(getResponseCode());
                //System.out.println("The respone code is : " + status);

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);
                //System.out.println(h.get("response").toString());
                regResponse = (String) h.get("response");
                //scoutDates = (Vector) h.get("root");
//                regResponse = Util.readToString(input);
                System.out.println("The response is " + regResponse);


            }
        };

        final NetworkManager manager = NetworkManager.getInstance();

        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //evt.consume();
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        //Dialog dlg = ip.showInifiniteBlocking();
        Dialog d = new Dialog();
        d.setDialogUIID("Container");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Loading...");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);



        String url = "http://107.20.238.53:8080/africanexttopmodel2013/Receiver";
        request.setUrl(url);
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setHttpMethod("POST");
        request.setTimeout(3000);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(d);

        manager.start();

        manager.addToQueueAndWait(request);

    }

    private void fetchScoutDates() {

        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Get the status of the connection        
//            @Override
//            protected void readHeaders(Object connection) throws IOException {
//
//                String heade = getHeader(connection, null);
//                System.out.println("All header field names : "+getHeaderFieldNames(connection));
//                 System.out.println("The status of the connection: " + heade);
//            }
            //*****************
//            @Override
//            public int getResponseCode() {
//                return super.getResponseCode(); //To change body of generated methods, choose Tools | Templates.
//            }
            @Override
            protected void readResponse(InputStream input) throws IOException {


                status = String.valueOf(getResponseCode());
                //System.out.println("The respone code is : " + status);

                // result = Result.fromContent(input, Result.XML);
                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);
                scoutDates = (Vector) h.get("root");
                //System.out.println(result);
            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //evt.consume();
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        //Dialog dlg = ip.showInifiniteBlocking();
        Dialog d = new Dialog();
        d.setDialogUIID("Container");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Loading...");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);


        String url = "http://www.mobile-hook.com/api/antm/scoutdates.php";
        //String url = ""http://antmafrica.com/locations.xml"
        request.setUrl(url);
        request.setContentType("application/json");

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(d);


        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.setTimeout(5000);
        manager.addToQueueAndWait(request);

    }

    public void fetchAllContestants() {

        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Get the status of the connection        
//            @Override
//            protected void readHeaders(Object connection) throws IOException {
//
//                status = getHeader(connection, null);
//                 System.out.println("The status of the connection: " + status);
//            }
            //*****************
            @Override
            protected void readResponse(InputStream input) throws IOException {


                status = String.valueOf(getResponseCode());
                //System.out.println("The respone code is : " + status);

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);
                contestantsFetched = (Vector) h.get("root");
                // System.out.println(h.toString());
//                String username = h.get("username").toString();
//                String email = h.get("email").toString();
//                appUser = new AppUsers(username, email);

                // System.out.println(contestantsFetched.toString());


            }
        };

        final NetworkManager manager = NetworkManager.getInstance();
        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //evt.consume();
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        //Dialog dlg = ip.showInifiniteBlocking();
        Dialog d = new Dialog();
        d.setDialogUIID("Container");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Loading...");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);


        String url = "http://www.mobile-hook.com/api/antm/contestants.php";
        request.setUrl(url);
        request.setContentType("application/json");
//        request.addRequestHeader("X-Parse-Application-Id", appId);
//        request.addRequestHeader("X-Parse-REST-API-Key", restApiKey);
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(d);


        //NetworkManager manager = NetworkManager.getInstance();
//        String[] api = manager.getAPIds();
//        System.out.println(api[0]);
//        System.out.println(api[1]);
        manager.start();
        manager.setTimeout(5000);
        manager.addToQueueAndWait(request);

    }

    private void fetchFromGallery() {
//        final ConnectionRequest request = new ConnectionRequest() {
////            // **************** Get the status of the connection        
////            @Override
////            protected void readHeaders(Object connection) throws IOException {
////
////                status = getHeader(connection, null);
////                 System.out.println("The status of the connection: " + status);
////            }
////            //*****************
//            @Override
//            protected void readResponse(InputStream input) throws IOException {
//
//                status = String.valueOf(getResponseCode());
//                //System.out.println("The respone code is : " + status);
//
//                JSONParser p = new JSONParser();
//                InputStreamReader inp = new InputStreamReader(input);
//                Hashtable h = p.parse(inp);
//                galleryImages = (Vector) h.get("root");
//                // System.out.println(h.toString());
////                String username = h.get("username").toString();
////                String email = h.get("email").toString();
////                appUser = new AppUsers(username, email);
//
//                // System.out.println(contestantsFetched.toString());
//
//
//            }
//        };
//
//        final NetworkManager manager = NetworkManager.getInstance();
//        Command c = new Command("Cancel") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                manager.killAndWait(request); //               evt.consume();
//                //evt.consume();
//            }
//        };
//
//        InfiniteProgress ip = new InfiniteProgress();
//        //Dialog dlg = ip.showInifiniteBlocking();
//        Dialog d = new Dialog();
//        d.setDialogUIID("Container");
//        d.setLayout(new BorderLayout());
//        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        Label l = new Label("Loading...");
//        l.getStyle().getBgTransparency();
//        cnt.addComponent(l);
//        cnt.addComponent(ip);
//        d.addComponent(BorderLayout.CENTER, cnt);
//        d.setTransitionInAnimator(CommonTransitions.createEmpty());
//        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
//        d.showPacked(BorderLayout.CENTER, false);
//        d.setBackCommand(c);
//
//
//        String url = "http://mobile-hook.com/api/antm/picgallery.php";
//        request.setUrl(url);
//        //request.setContentType("application/json");
////        request.addRequestHeader("X-Parse-Application-Id", appId);
////        request.addRequestHeader("X-Parse-REST-API-Key", restApiKey);
//        request.setFailSilently(true);//stops user from seeing error message on failure
//        request.setPost(false);
//        request.setDuplicateSupported(true);
//        request.setDisposeOnCompletion(d);
//
//
//        //NetworkManager manager = NetworkManager.getInstance();
//        manager.start();
//        manager.setTimeout(5000);
//        manager.addToQueueAndWait(request);

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                status = String.valueOf(getResponseCode());
                //System.out.println("The respone code is : " + status);

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);
                //System.out.println(h.get("data").toString());
                // regResponse = (String) h.get("response");
                galleryImages = new Vector<Hashtable>();
                Hashtable hash = (Hashtable) h.get("pagination");
                nextURL = (String) hash.get("next_url");
                System.out.println("pagination : " + hash);
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Vector fetchedData = (Vector) h.get("data");
                for (int i = 0; i < fetchedData.size(); i++) {
                    Hashtable object = (Hashtable) fetchedData.elementAt(i);
                    Hashtable h3 = (Hashtable) object.get("images");
                    galleryImages.add((Hashtable) h3.get("standard_resolution"));
                    System.out.println("standard resolution : " + h3.get("standard_resolution"));
                }
//                regResponse = Util.readToString(input);
                //System.out.println("The response is " + fetchedData);


            }
        };

        final NetworkManager manager = NetworkManager.getInstance();

        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //evt.consume();
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        //Dialog dlg = ip.showInifiniteBlocking();
        Dialog d = new Dialog();
        d.setDialogUIID("Container");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("populating gallery");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);



        String url = "https://api.instagram.com/v1/users/self/media/recent/?access_token=286661039.eab64d9.3c206753fe1440cf9d45fdfa99d68a11";
        request.setUrl(url);
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        request.setHttpMethod("GET");
        request.setTimeout(3000);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(d);

        manager.start();
        manager.addToQueueAndWait(request);
    }

    public void fetchAllNews() {

        final ConnectionRequest request = new ConnectionRequest() {
            // **************** Get the status of the connection        
//            @Override
//            protected void readHeaders(Object connection) throws IOException {
//
//                status = getHeader(connection, null);
//                 System.out.println("The status of the connection: " + status);
//            }
            //*****************
            @Override
            protected void readResponse(InputStream input) throws IOException {


                status = String.valueOf(getResponseCode());
                //System.out.println("The respone code is : " + status);

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);
                allNews = (Vector) h.get("root");

//                String username = h.get("username").toString();
//                String email = h.get("email").toString();
//                appUser = new AppUsers(username, email);

                //System.out.println(contestantsFetched.toString());


            }
        };


        final NetworkManager manager = NetworkManager.getInstance();
        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //evt.consume();//                evt.consume();
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        //Dialog dlg = ip.showInifiniteBlocking();
        Dialog d = new Dialog();
        d.setDialogUIID("Container");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Loading...");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);


        String url = "http://www.mobile-hook.com/api/antm/news.php";
        request.setUrl(url);
        request.setContentType("application/json");
//        request.addRequestHeader("X-Parse-Application-Id", appId);
//        request.addRequestHeader("X-Parse-REST-API-Key", restApiKey);
        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(d);


        //NetworkManager manager = NetworkManager.getInstance();
        manager.start();
        manager.setTimeout(5000);
        manager.addToQueueAndWait(request);

    }

    private void doGeocode(String address) throws NullPointerException {

        final ConnectionRequest request = new ConnectionRequest() {
//            @Override
//            protected void readHeaders(Object connection) throws IOException {
//
//                status = getHeader(connection, null);
//                 System.out.println("The status of the connection: " + status);
//            }
            @Override
            protected void readResponse(InputStream input) throws IOException {


                status = String.valueOf(getResponseCode());
                //System.out.println("The respone code is : " + status);

                JSONParser p = new JSONParser();
                InputStreamReader inp = new InputStreamReader(input);
                Hashtable h = p.parse(inp);

                status2 = (String) h.get("status");
                // System.out.println(" THE STATUS " + status);
                final Vector v = (Vector) h.get("results");
                // final Vector v2 = (Vector)v.elementAt(0);
                // System.out.println("------------------From Google Map--------------------------------");
                // System.out.println(" THE RESULT " + v.toString());
                // System.out.println(" THE RESULT 2 "+v2.toString());

                for (int i = 0; i < v.size(); i++) {
                    Hashtable entry = (Hashtable) v.elementAt(0);
                    Hashtable geo = (Hashtable) entry.get("geometry");
                    Hashtable loc = (Hashtable) geo.get("location");
                    latt = (Double) loc.get("lat");
                    longs = (Double) loc.get("lng");

                    // System.out.println("Lattitude  " + latt);
                    // System.out.println("Longitude  " + longs);


                }


            }
        };


        final NetworkManager manager = NetworkManager.getInstance();
        Command c = new Command("Cancel") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                manager.killAndWait(request);
                //evt.consume();
            }
        };

        InfiniteProgress ip = new InfiniteProgress();
        //Dialog dlg = ip.showInifiniteBlocking();
        Dialog d = new Dialog();
        d.setDialogUIID("Container");
        d.setLayout(new BorderLayout());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Loading...");
        l.getStyle().getBgTransparency();
        cnt.addComponent(l);
        cnt.addComponent(ip);
        d.addComponent(BorderLayout.CENTER, cnt);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createEmpty());
        d.showPacked(BorderLayout.CENTER, false);
        d.setBackCommand(c);


        String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
        request.setUrl(url);

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(true);
        request.setDuplicateSupported(true);
        request.setDisposeOnCompletion(d);
        
        manager.start();
        manager.setTimeout(5000);
        manager.addToQueueAndWait(request);
    }

    @Override
    protected void beforeMain(Form f) {

        Display.getInstance().lockOrientation(true);
        //Storage.getInstance().deleteStorageFile("Contestant");
        Image oluchiImage = fetchResourceFile().getImage("splashscreen.jpg");
        findSplashLogo(f).setIcon(oluchiImage.scaledWidth(Display.getInstance().getDisplayWidth()));
//        f.getStyle().setBgImage(oluchiImage);
//        f.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        

        //Storage.getInstance().deleteStorageFile("AntmUser");
    }

    @Override
    protected void onAppRegister_CreateAppUserAction(Component c, ActionEvent event) {

        String username = findNewUserUsernameTextField(c.getComponentForm()).getText();
        String email = findNewUserEmailTextField(c.getComponentForm()).getText();
        String country = findCountryComboBox(c.getComponentForm()).getSelectedItem().toString();
        String number = findAppUserPhoneNumberTextField(c.getComponentForm()).getText();
        //number.replace((number.charAt(0)), response);
        if (number.startsWith("0")) {
            number = number.substring(1);
        }
        final String newNumber1 = (findSelectedCountryCode(c.getComponentForm()).getText()) + number;
        System.out.println(newNumber1);
        if (("".equals(username)) || ("".equals(email)) || ("".equals(number))) {
            Dialog.show("", "all fields are required", "OK", null);
        } else if (("choose your country".equals(country)) || (("----Others".equals(country)) && ("".equals((String) findOtherCountryTextField(c.getComponentForm()).getText())))) {
            Dialog.show("", "you have not chosen your country", "OK", null);
        } else {
//(("choose your country".equals(country)) || (("----Others".equals(country)) && ("".equals((String)findOtherCountryTextField(c.getComponentForm()).getText()))))
            if (("----Others".equals(country)) || ("".equals(country))) {
                String enteredCountry = findOtherCountryTextField(c.getComponentForm()).getText();
                if ("".equals(enteredCountry)) {
                    Dialog.show("", "please enter your country", "OK", null);
                } else {
                    if (!(number.startsWith("+"))) {
                        //number = number.substring(1);
                        Dialog.show("", "country code not detected on the phone number. all country codes start with '+'", "OK", null);
                    } else {
                        registerAppUser(username, email, enteredCountry, number);
                        if (status == null || (!("200".equals(status)))) {
                            Dialog.show("", "you may not be connected to the internet", "OK", null);
                        } else {
                            status = "";
                            if ("user registered".equals(regResponse)) {
                                Hashtable data = new Hashtable();
                                data.put("name", username);
                                data.put("country", enteredCountry);
                                data.put("email", email);
                                data.put("phone", newNumber1);

                                try {
                                    Storage.getInstance().writeObject("AntmUser", data);
                                } catch (Exception e) {
                                    Dialog.show("Error !!!", "error reading Storage media " + "'" + e.getMessage() + "'", "OK", null);
                                }

                                Dialog.show("", "User " + "'" + username + "'" + " has been Created", "OK", null);
                                showForm("Menu", null);
                            } else {
                                Dialog.show("", "could not create User", "OK", null);
                            }

                        }
                    }

                }
            } else {
                registerAppUser(username, email, country, newNumber1);
                if (status == null || (!("200".equals(status)))) {
                    Dialog.show("", "you may not be connected to the internet", "OK", null);
                } else {
                    status = "";
                    if ("user registered".equals(regResponse)) {
                        Hashtable data = new Hashtable();
                        data.put("name", username);
                        data.put("country", country);
                        data.put("email", email);
                        data.put("phone", newNumber1);

                        try {
                            Storage.getInstance().writeObject("AntmUser", data);
                        } catch (Exception e) {
                            Dialog.show("Error !!!", "error reading Storage media " + "'" + e.getMessage() + "'", "OK", null);
                        }

                        Dialog.show("", "User " + "'" + username + "'" + " has been Created", "OK", null);
                        showForm("Menu", null);
                    } else {
                        Dialog.show("", "could not create User", "OK", null);
                    }
                }
            }
        }
    }

    private Container addContestantPix2(final String cid, final String imageURL, final String surname, final String firstname, final String age, String dob,
            final String country, final String desc, final String height) {

        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachContestant");
        Button b = findContestantPicture(c);
        b.setText(firstname);
        //b.getStyle().setBgImage(i.scaledWidth(Display.getInstance().getDisplayHeight() / 2));//Icon(i);
        //b.setIcon(i.scaledWidth(Display.getInstance().getDisplayWidth() / 6));
        //ImageDownloadService.createImageToStorage(dob, b, appId, photo, null);
        ImageDownloadService.createImageToStorage(imageURL + ";deviceside=true", b, cid, new Dimension(Display.getInstance().getDisplayWidth() / 3, Display.getInstance().getDisplayHeight() / 3));
        //b.setIcon(i.scaledWidth(40));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // System.out.println("you clicked " + surname + " " + firstname);
                contestant = new Contestants(cid, surname, firstname, country, age, height, desc, imageURL);
                showForm("SelectedContestant", null);
                //Dialog.show("", desc, "OK", null);
            }
        });

        //System.out.println("j is " + j);
        return c;
    }

    private Container addContestantPix(final int j, Image i, String name) {

        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachContestant");
        Button b = findContestantPicture(c);
        b.setText(name);
        //b.getStyle().setBgImage(i.scaledWidth(Display.getInstance().getDisplayHeight() / 2));//Icon(i);
        b.setIcon(i.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
        //b.setIcon(i.scaledWidth(40));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // System.out.println("clicked " + j);
                Dialog.show("", "No Contestants yet", "OK", null);
            }
        });

        return c;
    }

    @Override
    protected void beforeMenu(Form f) {
        Display.getInstance().unlockOrientation();//lockOrientation(true);
        //Storage.getInstance().clearStorage();
        f.setScrollable(false);
        Image oluchiImage = fetchResourceFile().getImage("oluchi.png");
        findLabelButton(f).setIcon(oluchiImage.scaledWidth(Display.getInstance().getDisplayWidth() - 20));
//        findContainer1(f).getStyle().setBgImage(oluchiImage);
//        findContainer1(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        Command close = new Command("Exit") {
            @Override
            public void actionPerformed(ActionEvent evt) {

//                showForm("LoginPage", null);
                Display.getInstance().exitApplication();
            }
        };

        f.getMenuBar().addCommand(close);
       
        //Image scout = fetchResourceFile().getImage("scouting.jpg");
        Image contest = fetchResourceFile().getImage("contestants.jpg");
        Image newsB = fetchResourceFile().getImage("news_2.jpg");
        Image gallerypix = fetchResourceFile().getImage("gallery_2.jpg");
        Image partnerB = fetchResourceFile().getImage("partners_2.jpg");

        Vector<Hashtable> buttonLogos = new Vector<Hashtable>();

//        Hashtable h1 = new Hashtable();
//        h1.put("logo", scout);
//        h1.put("name", "Scouting");

        Hashtable h2 = new Hashtable();
        h2.put("logo", contest);
        h2.put("name", "Contestants");

        Hashtable h3 = new Hashtable();
        h3.put("logo", newsB);
        h3.put("name", "News");

        Hashtable h4 = new Hashtable();
        h4.put("logo", gallerypix);
        h4.put("name", "GalleryButton");

        Hashtable h5 = new Hashtable();
        h5.put("logo", partnerB);
        h5.put("name", "Partners");

        //buttonLogos.add(h1);
        buttonLogos.add(h2);
        buttonLogos.add(h3);
        buttonLogos.add(h4);
        buttonLogos.add(h5);

        Container c1 = findContainer(f);

        for (int i = 0; i < buttonLogos.size(); i++) {
            Hashtable hashtable = buttonLogos.get(i);
            Image image = (Image) hashtable.get("logo");
            c1.addComponent(addMenuButton(image, hashtable.get("name").toString()));
        }
    }

    private Container addMenuButton(Image i, final String nm) {
        //MenuButtonsPix
        Resources res = fetchResourceFile();
        Container c = createContainer(res, "MenuButtonsPix");
        Button b = findMenuButton(c);
        b.setIcon(i.scaledWidth(Display.getInstance().getDisplayWidth() / 5));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if ("News".equals(nm)) {
                    showForm("NewsView", null);
//                    if ((allNews == null) || (allNews.isEmpty())) {
//                        
//                        fetchAllNews();
//                        if ((!(status == null)) && ("200".equals(status)) && (!(allNews == null)) && (!allNews.isEmpty())) {
//                            showForm("NewsView", null);
//                        } else {
//                            Dialog.show("oh!!! dear", "could not fetch News", "OK", null);
//                        }
//                    } else {
//                        showForm("NewsView", null);
//                    }

                } else if ("Scouting".equals(nm)) {
                    //Dialog.show("selected", "Scouting things", "OK", null);
                    if ((scoutDates == null) || (scoutDates.isEmpty())) {
                        fetchScoutDates();
                        if ((!(status == null)) && ("200".equals(status))) {

                            if ((scoutDates == null) || (scoutDates.isEmpty())) {
                                Dialog.show("Yikes!!!", "data not fetched", "OK", null);
                            } else {
                                showForm("ScoutingPlaces", null);
                            }

                        } else {
                            Dialog.show("oh!!! dear", "could not download Scouting places", "OK", null);
                        }
                    } else {
                        showForm("ScoutingPlaces", null);
                    }
                } else if ("Contestants".equals(nm)) {
                    fetchAllContestants();
                    if (!(status == null) && ("200".equals(status))) {
                        showForm("ContestantsView", null);
                    } else {
                        Dialog.show("oh!!! dear", "could not fetch All Contestants", "OK", null);
                    }
                    //  showForm("ContestantsView", null);
                } else if ("GalleryButton".equals(nm)) {
                    //Dialog.show("Gallery", "No data yet in Gallery", "OK", null);
                    if ((galleryImages == null) || (galleryImages.isEmpty())) {
                        fetchFromGallery();
                        if (!(status == null) && ("200".equals(status)) && (!(galleryImages == null)) && (!galleryImages.isEmpty())) {
                            showForm("Gallery", null);
                        } else {
                            Dialog.show("oh!!! dear", "could not fetch images", "OK", null);
                        }
                    } else {
                        showForm("Gallery", null);
                    }
                } else if ("Partners".equals(nm)) {
                    showForm("PartnersView", null);
                }
            }
        });
        return c;
    }

    @Override
    protected void beforePartnersView(Form f) {
        Display.getInstance().unlockOrientation();//lockOrientation(false);
        f.setScrollable(false);

        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("Menu", null);
                //back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer2(f).getStyle().setBgImage(bghead);
        findContainer2(f).getStyle().setBorder(null);
        findContainer2(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer2(f).addComponent(BorderLayout.WEST, bc);

        findLabel(f).setText("Our Partners");

//        Command close = new Command("Home") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//                showForm("Menu", null);
//                //Display.getInstance().exitApplication();
//            }
//        };
//
//
//        f.getMenuBar().addCommand(close);
//
//        Image bgwhite = fetchResourceFile().getImage("WHITEBACKGROUND.png");
//
//        f.getStyle().setBgImage(bgwhite);
//        f.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        Image cbc = fetchResourceFile().getImage("cbs.png");
        Image mnet = fetchResourceFile().getImage("mnet.png");
        Image afmag = fetchResourceFile().getImage("african magic.png");
        Image etisalat = fetchResourceFile().getImage("etisalat.jpg");
        Image gentTouch = fetchResourceFile().getImage("NaturesGentleTouch.png");
        Image pAndG = fetchResourceFile().getImage("P&G.png");
        Image snap = fetchResourceFile().getImage("snapp.png");
        Image verve = fetchResourceFile().getImage("VERVE.png");
        Image sat = fetchResourceFile().getImage("SAT.png");
        Image dna = fetchResourceFile().getImage("dna.png");

        Vector<Hashtable> partners = new Vector<Hashtable>();

        Hashtable h1 = new Hashtable();
        h1.put("logo", cbc);
        h1.put("name", "CBC Broadcasting");

        Hashtable h2 = new Hashtable();
        h2.put("logo", mnet);
        h2.put("name", "MNET Africa");

        Hashtable h3 = new Hashtable();
        h3.put("logo", afmag);
        h3.put("name", "Africa Magic");

        Hashtable h4 = new Hashtable();
        h4.put("logo", sat);
        h4.put("name", "South Africa Tourism");

        Hashtable h5 = new Hashtable();
        h5.put("logo", dna);
        h5.put("name", "DNA Model managers");
        
        Hashtable h6 = new Hashtable();
        h6.put("logo", etisalat);
        h6.put("name", "Etisalat");
        
        Hashtable h7 = new Hashtable();
        h7.put("logo", gentTouch);
        h7.put("name", "Nature's gentle Touch");
        
        Hashtable h8 = new Hashtable();
        h8.put("logo", pAndG);
        h8.put("name", "P and G");
        
        Hashtable h9 = new Hashtable();
        h9.put("logo", snap);
        h9.put("name", "Snap");
        
        Hashtable h10 = new Hashtable();
        h10.put("logo", verve);
        h10.put("name", "Verve");

        partners.add(h6);
        partners.add(h2);
        partners.add(h3);
        partners.add(h4);
        partners.add(h5);
        partners.add(h9);
        partners.add(h1);
        partners.add(h8);
        partners.add(h10);     
        partners.add(h7);     
        
        findContainer(f).removeAll();
        for (int i = 0; i < partners.size(); i++) {
            Hashtable hashtable = partners.get(i);
            Image image = (Image) hashtable.get("logo");
            findContainer(f).addComponent(addPartner(image, hashtable.get("name").toString()));
        }


    }

    public Container addPartner(Image i, String name) {

        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachPartner");
        // c.removeAll();
        findPartnerLogo(c).setIcon(i.scaledWidth(Display.getInstance().getDisplayWidth() / 4));
        //findPartnerName(c).setText(name);
        return c;
    }

    @Override
    protected void beforeNewsView(Form f) {
        Display.getInstance().unlockOrientation();
        f.setScrollable(false);

        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("Menu", null);
//                back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer(f).getStyle().setBgImage(bghead);
        findContainer(f).getStyle().setBorder(null);
        findContainer(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer(f).addComponent(BorderLayout.WEST, bc);
        findLabel(f).setText("News");

//        Command close = new Command("Home") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//                showForm("Menu", null);
//                //Display.getInstance().exitApplication();
//            }
//        };
//
//
//        f.getMenuBar().addCommand(close);
        Image bgwhite = fetchResourceFile().getImage("WHITEBACKGROUND.png");

        Display.getInstance().getDisplayHeight();
        Display.getInstance().getDisplayWidth();

        f.getStyle().setBgImage(bgwhite);
        f.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        findContainer1(f).removeAll();
        BrowserComponent bcw = new BrowserComponent();        
        bcw.setURL("http://m.facebook.com/ANTMAFRICA");
        
        findContainer1(f).addComponent(BorderLayout.CENTER, bcw);
        
        Dialog d = new Dialog();
        TextArea t = new TextArea();
        t.setEditable(false);
        t.setText("please wait, it takes a bit of time to load news page");
        d.addComponent(t);
        d.setTimeout(3000);
        d.show();
//        Container c = findContainer1(f);
//        for (int i = 0; i < allNews.size(); i++) {
//            Hashtable hashtable = allNews.get(i);
//            //System.out.println("Object number "+i+" = "+hashtable);
//            c.addComponent(addNews((String) hashtable.get("title"), (String) hashtable.get("details")));
//        }
    }

    private Container addNews(final String title, final String details) {
        //NewsRenderer
        Resources res = fetchResourceFile();
        Container c = createContainer(res, "NewsRenderer");

        Button b = findNewsRendererNewsTitle(c);
        b.setText(title);
//        System.out.println("Title : "+ title);
//        System.out.println("Details : "+ details);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                news = new News(title, details);

                showForm("EachNews", null);
            }
        });

        return c;
    }

    @Override
    protected void onAppRegister_CancelCreateAction(Component c, ActionEvent event) {

        Display.getInstance().exitApplication();
    }

    @Override
    protected void beforeContestantsView(Form f) {
        Display.getInstance().unlockOrientation();//lockOrientation(false);
//        f.setScrollable(false);//Title("Contestants");

        f.setScrollable(false);
        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                showForm("Menu", null);
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer3(f).getStyle().setBgImage(bghead);
        findContainer3(f).getStyle().setBorder(null);
        findContainer3(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer3(f).addComponent(BorderLayout.WEST, bc);
        findLabel(f).setText("Contestants");

        Image bgwhite = fetchResourceFile().getImage("WHITEBACKGROUND.png");

        f.getStyle().setBgImage(bgwhite);
        f.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        Container c = findContestantsContainer(f);
        c.removeAll();

        if ((contestantsFetched == null) || (contestantsFetched.isEmpty())) {
            for (int i = 0; i < 12; i++) {
                //System.out.println(i);
                Image contImage = fetchResourceFile().getImage("placeholder.png");
                c.addComponent(addContestantPix(i, contImage, ""));
            }
        } else {
            for (int i = 0; i < contestantsFetched.size(); i++) {
                //System.out.println(i);
                Hashtable h = contestantsFetched.get(i);
                //Image contImage = fetchResourceFile().getImage("placeholder.png");
                c.addComponent(addContestantPix2(h.get("cid").toString(), h.get("imageurl").toString(), h.get("surname").toString(), h.get("firstname").toString(), h.get("age").toString(), h.get("dob").toString(), h.get("country").toString(), h.get("sdescription").toString(), h.get("height").toString()));
            }
        }

    }

    @Override
    protected boolean allowBackTo(String formName) {
        //return super.allowBackTo(formName); //To change body of generated methods, choose Tools | Templates.
        if (("Main".equals(formName)) || ("AppRegister".equals(formName))) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean processBackground(final Form f) {
//Scouting
        //contestants
        //News
        //Gallery
        // Partners
        super.processBackground(f);
        if ("Main".equals(f.getName())) {
            if (Storage.getInstance().exists("AntmUser")) {

                showForm("Menu", null);
                return false;

            }
            //return true;//loadDataFromStorage();
        }
        return true;
    }

    public Container addContestant(String pix, String name, String age, String height, String country) throws IOException {

        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachContestant");
        //Image i = Image.createImage(pix);
        // c.removeAll();
        //findPicture(c).getStyle().setBgImage(i.scaled((Display.getInstance().getDisplayHeight() / 2), (Display.getInstance().getDisplayWidth() / 2)));
        //.setIcon(photo.scaledWidth(Display.getInstance().getDisplayWidth() / 3));
        findContestantName(c).setText(name);
        findContestantAge(c).setText(age);
        findContestantCountry(c).setText(country);
        findContestantHeight(c).setText(height);

        return c;
    }

//    @Override
//    protected void onEachNews_OKButtonAction(Component c, ActionEvent event) {
//
//        back();
//    }
    @Override
    protected void beforeEachNews(Form f) {
        Display.getInstance().unlockOrientation();
        f.setScrollable(false);
        Image btnHOME = fetchResourceFile().getImage("home.png");
        Image btnShare = fetchResourceFile().getImage("share_icon.png");

        ShareButton sb = new ShareButton();
        sb.setTextToShare("ANTM AFRICA: " + news.getTitle() + ". see details here https://www.facebook.com/ANTMAfrica");
        //System.out.println("ANTM AFRICA: "+news.getTitle()+". see details here https://www.facebook.com/ANTMAfrica");
        sb.setIcon(btnShare);
        sb.setUIID("Label");
//        Button b = new Button();
//        b.setUIID("Label");
//        b.setIcon(btnShare);
//        b.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent evt) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                Display.getInstance().share(news.getDetails(), null, "text/plain");
//                
//            }
//        });
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("Menu", null);
                //back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer2(f).getStyle().setBgImage(bghead);
        findContainer2(f).getStyle().setBorder(null);
        findContainer2(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer2(f).addComponent(BorderLayout.WEST, bc);
        findContainer(f).addComponent(BorderLayout.EAST, sb);
        findLabel1(f).setText("News");

        findNewsTitleTextField(f).setText(news.getTitle());
        findNewsDetailTextArea(f).setText(news.getDetails());
        //Display.getInstance().share(status, imagePath, myKey);

    }

    // the layout. lock orientation. fix button to the bottom
    public Container addScoutDate(final String date, final String address, final String country, final String time) throws IOException {

        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachScoutPlace");
        MultiButton b = findScoutAddress(c);
        //findScoutDate(c).setText(date);
        b.setTextLine1(address);
        b.setTextLine2(country);
        b.setTextLine3(date + " time from : " + time);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                // System.out.println(country);
                //System.out.println((address.replace(' ', '+')) + "+" + country);
                doGeocode((address.replace(' ', '+')) + "+" + country.replace(' ', '+'));
                if ("200".equals(status)) {
                    status = "";
                    if (("OK".equals(status2)) && (!(latt == 0.0) && !(longs == 0.0))) {
                        scoutingVenue = new ScoutingVenue(address, country, date, latt, longs);
                        showForm("selectedScoutVenue", null);
                    } else {
                        Dialog.show("", "could not be located on the map", "OK", null);
                    }
                } else {
                    Dialog.show("", "you may not be connected to the internet", "OK", null);
                }
            }
        });
        return c;
    }

    @Override
    protected void beforeScoutingPlaces(Form f) {
        Display.getInstance().unlockOrientation();
        f.setScrollable(false);
        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("Menu", null);
                //back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer(f).getStyle().setBgImage(bghead);
        findContainer(f).getStyle().setBorder(null);
        findContainer(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer(f).addComponent(BorderLayout.WEST, bc);
        findLabel(f).setText("Scouting Places");
//        findLabel(f).getStyle().setBgImage(bgwhite);
//        findLabel(f).getStyle().setBackgroundType(Style.TEXT_DECORATION_3D);

        Container c = findScoutContainer(f);
        for (int i = 0; i < scoutDates.size(); i++) {
            try {
                //byte b = photoByte[i];
                Hashtable h = (Hashtable) scoutDates.get(i);
                c.addComponent(addScoutDate(h.get("date").toString(), h.get("address").toString(), h.get("country").toString(), h.get("time").toString()));
            } catch (IOException ex) {
                //Logger.getLogger(StateMachine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void beforeSelectedScoutVenue(Form f) {

        f.removeComponent(findScoutMapComponent(f));
        Coord lastLocation = null;

        final MapComponent mc = new MapComponent(new GoogleMapsProvider(myKey));
        f.setScrollable(false);

        lastLocation = new Coord(scoutingVenue.getLattitude(), scoutingVenue.getLongitude());
        // mc.zoomTo(crd, 6);

        PointsLayer pl = new PointsLayer();
        Image redPin = fetchResourceFile().getImage("red_pin.png");
        pl.setPointIcon(redPin);

        //System.out.println("My lastlocation is " + lastLocation);

        PointLayer p = new PointLayer(lastLocation, scoutingVenue.getAddress(), redPin);
        //p.setDisplayName(true);
        pl.addPoint(p);
        mc.removeAllLayers();
        mc.addLayer(pl);

        mc.zoomTo(lastLocation, 15);
        f.addComponent(BorderLayout.CENTER, mc);

    }

    @Override
    protected void beforeAppRegister(Form f) {

        Display.getInstance().unlockOrientation();
        f.setScrollable(false);
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer3(f).getStyle().setBgImage(bghead);
        findContainer3(f).getStyle().setBorder(null);
        findContainer3(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        //findContainer1(f).addComponent(BorderLayout.WEST, bc);
        findLabel1(f).setText("Registration");

        findLabel3(f).setVisible(false);
        findOtherCountryTextField(f).setVisible(false);
        findOtherCountryTextField(f).setEnabled(false);
        findAppUserPhoneNumberTextField(f).setText(Display.getInstance().getMsisdn());

        findAppUserPhoneNumberTextField(f).setEditable(false);
        Vector<Hashtable> ctryCodes;
        countryCodeStream = Display.getInstance().getResourceAsStream(this.getClass(), "/CountryCodes.json");
//        try {
//            Media m = MediaManager.createMedia("", false);
//        } catch (IOException ex) {
//            Logger.getLogger(StateMachine.class.getName()).log(Level.SEVERE, null, ex);
//        }
        JSONParser p = new JSONParser();
        InputStreamReader inp = new InputStreamReader(countryCodeStream);
        Hashtable h = null;
        try {
            h = p.parse(inp);
        } catch (IOException ex) {
            Dialog.show("Oh dear!!!", ex.getMessage(), "OK", null);
            //Logger.getLogger(StateMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
        ctryCodes = (Vector) h.get("root");
        ComboBox box = findCountryComboBox(f);

        for (int i = 0; i < ctryCodes.size(); i++) {
            Hashtable hashtable = ctryCodes.elementAt(i);
            box.addItem(hashtable.get("country"));
        }

        Command close = new Command("Exit") {
            @Override
            public void actionPerformed(ActionEvent evt) {

//                showForm("LoginPage", null);
                Display.getInstance().exitApplication();
            }
        };


        f.getMenuBar().addCommand(close);
    }

    @Override
    protected void onAppRegister_CountryComboBoxAction(Component c, ActionEvent event) {

        String country = findCountryComboBox(c.getComponentForm()).getSelectedItem().toString();
        //System.out.println(findCountryComboBox(c.getComponentForm()).getSelectedItem());
        findAppUserPhoneNumberTextField(c.getComponentForm()).setEditable(true);
        //Hashtable h = (Hashtable) findCountryComboBox(c.getComponentForm()).getSelectedItem();


        countryCodeStream = Display.getInstance().getResourceAsStream(this.getClass(), "/CountryCodes.json");
        Vector<Hashtable> ctryCodes;
        JSONParser p = new JSONParser();
        InputStreamReader inp = new InputStreamReader(countryCodeStream);
        Hashtable h = null;
        try {
            h = p.parse(inp);
        } catch (IOException ex) {
            Dialog.show("Oh dear!!!", ex.getMessage(), "OK", null);
            //Logger.getLogger(StateMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
        ctryCodes = (Vector) h.get("root");
//System.out.println(ctryCodes);
        if ("----Others".equals(country)) {
            //Dialog.show("", "enter your country code along side your phone number", "OK", null);
            findSelectedCountryCode(c.getComponentForm()).setVisible(false);
            //findLabel3(c.getComponentForm()).setVisible(true);
            findOtherCountryTextField(c.getComponentForm()).setVisible(true);
            findOtherCountryTextField(c.getComponentForm()).setEnabled(true);
            findOtherCountryTextField(c.getComponentForm()).requestFocus();
        } else {
            findLabel3(c.getComponentForm()).setVisible(true);
            for (int i = 0; i < ctryCodes.size(); i++) {
                Hashtable hashtable = ctryCodes.elementAt(i);
                if (hashtable.get("country").equals(country)) {
                    findSelectedCountryCode(c.getComponentForm()).setText("+" + hashtable.get("code").toString());
                }

            }

            findLabel3(c.getComponentForm()).setVisible(false);
            findOtherCountryTextField(c.getComponentForm()).setVisible(false);
            findOtherCountryTextField(c.getComponentForm()).setEnabled(false);
        }
    }

    @Override
    protected void beforeGallery(Form f) {
        f.setScrollable(false);

        Display.getInstance().unlockOrientation();

        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("Menu", null);
                //back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer1(f).getStyle().setBgImage(bghead);
        findContainer1(f).getStyle().setBorder(null);
        findContainer1(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer1(f).addComponent(BorderLayout.WEST, bc);
        findLabel(f).setText("Gallery");

        for (int i = 0; i < galleryImages.size(); i++) {
            Hashtable hashtable = galleryImages.get(i);
            findContainer(f).addComponent(addGalleryPix(hashtable.get("url").toString(),i,f));
        }
    }

    private Container addGalleryPix(final String imageurl, int i, final Form f) {
        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachGalleryPixZoom");

        final Button b = findGalleryImage(c);
        //b.setText(cid);
       // b.setTextPosition(2);
//        b.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//
//                final Label l = new Label();
//                if (Display.getInstance().getDisplayWidth() > Display.getInstance().getDisplayHeight()) {
//                    // l.setIcon(b.getIcon().scaled(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight()));
//                    //ImageDownloadService.createImageToStorage(imageurl + ";deviceside=true", l, cid, new Dimension(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight()));
//                    final ConnectionRequest request = new ConnectionRequest() {
//                        @Override
//                        protected void readResponse(InputStream input) throws IOException {
//
//                            EncodedImage image = EncodedImage.create(input);
//                            l.setIcon(image.scaled(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight()));
//
//                        }
//                    };
//
//
//                    final NetworkManager manager = NetworkManager.getInstance();
//
//
//                    //String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
//                    request.setUrl(imageurl);
//
//                    request.setFailSilently(true);//stops user from seeing error message on failure
//                    request.setPost(false);
//                    request.setDuplicateSupported(true);
//
//                    manager.start();
//                    manager.setTimeout(5000);
//                    manager.addToQueue(request);
//
//                } else {
//                    // l.setIcon(b.getIcon().scaled(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight() / 2));
//                    //ImageDownloadService.createImageToStorage(imageurl + ";deviceside=true", l, cid, new Dimension(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight() / 2));
//                    final ConnectionRequest request = new ConnectionRequest() {
//                        @Override
//                        protected void readResponse(InputStream input) throws IOException {
//
//                            EncodedImage image = EncodedImage.create(input);
//                            l.setIcon(image.scaled(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight()));
//
//                        }
//                    };
//
//
//                    final NetworkManager manager = NetworkManager.getInstance();
//
//
//                    //String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
//                    request.setUrl(imageurl);
//
//                    request.setFailSilently(true);//stops user from seeing error message on failure
//                    request.setPost(false);
//                    request.setDuplicateSupported(true);
//
//                    manager.start();
//                    manager.setTimeout(5000);
//                    manager.addToQueue(request);
//
//                }
//                //l.setIcon(b.getIcon().scaled(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight() / 2));
//                //ImageDownloadService.createImageToStorage(imageurl, l, cid, new Dimension(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight() / 2));
//                Command[] cmds = new Command[2];
//                cmds[0] = new Command("OK") {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                    }
//                };
//                cmds[1] = new Command("View All") {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        showForm("ZoomedGallery", null);
//                    }
//                };
////                cmds[2] = new Command("Share") {
////                    @Override
////                    public void actionPerformed(ActionEvent evt) {
////                        //Image btnShare = fetchResourceFile().getImage("share_icon.png");
//////
//////                        ShareButton sb = new ShareButton();
//////                        sb.setImageToShare(imageurl, "image/jpeg");//TextToShare(news.getDetails());
////                        //sb.setIcon(btnShare);
////                        //sb.setUIID("Label");
////                        Display.getInstance().share(imageurl, null, "image/jpeg");
////                    }
////                };
//               // Dialog.show(cid, l, cmds);
//            }
//        });

        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                EncodedImage image = EncodedImage.create(input);
                b.setIcon(image.scaledWidth(Display.getInstance().getDisplayWidth()));
                f.revalidate();
            }
        };


        final NetworkManager manager = NetworkManager.getInstance();


        //String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
        request.setUrl(imageurl);

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        request.setDuplicateSupported(true);

        manager.start();
        manager.setTimeout(5000);
        manager.addToQueue(request);
        //ImageDownloadService.createImageToStorage(imageurl, b, cid, new Dimension(Display.getInstance().getDisplayWidth() / 6, Display.getInstance().getDisplayHeight() / 6));
        return c;
    }

    private Container addPixToZoom(final String cid, final String imageurl) {
        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachGalleryPix");

        final ShareButton b = findGalleryImageToZoom(c);
        b.setText(cid);
        b.setTextPosition(2);
        b.setImageToShare(imageurl, "image/jpeg");
        final ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                EncodedImage image = EncodedImage.create(input);
                b.setIcon(image.scaledWidth(Display.getInstance().getDisplayWidth() / 2));

            }
        };


        final NetworkManager manager = NetworkManager.getInstance();


        //String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
        request.setUrl(imageurl);

        request.setFailSilently(true);//stops user from seeing error message on failure
        request.setPost(false);
        request.setDuplicateSupported(true);

        manager.start();
        manager.setTimeout(5000);
        manager.addToQueue(request);


        return c;
    }

    @Override
    protected void beforeZoomedGallery(Form f) {
        f.setScrollable(false);
        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("Menu", null);
                //back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer1(f).getStyle().setBgImage(bghead);
        findContainer1(f).getStyle().setBorder(null);
        findContainer1(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        findContainer1(f).addComponent(BorderLayout.WEST, bc);
        findLabel(f).setText("Gallery");

        findContainer(f).setScrollableX(true);
        for (int i = 0; i < galleryImages.size(); i++) {
            //System.out.println(i);
            Hashtable hashtable = galleryImages.get(i);
            findContainer(f).addComponent(addPixToZoom(hashtable.get("cid").toString(), hashtable.get("imageurl").toString()));
        }
    }

    @Override
    protected void beforeSelectedContestant(Form f) {

        f.setScrollable(false);

        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showForm("Menu", null);
                //back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer1(f).getStyle().setBgImage(bghead);
        findContainer1(f).getStyle().setBorder(null);
        findContainer1(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer1(f).addComponent(BorderLayout.WEST, bc);
        findLabel(f).setText("Contestant");

        findSelectedContestantName(f).setText(contestant.getSurname() + " " + contestant.getFirstname());
        findSelectedContestantAge(f).setText("Age :" + contestant.getAge());
        findSelectedContestantCountry(f).setText("Country : " + contestant.getCountry());
        findSelectedContestantAboutMe(f).setText(contestant.getDescription());
        final Button b = findSelectedContestantPix(f);//+";deviceside=true" +";deviceside=true"
        if (Display.getInstance().getDisplayWidth() > Display.getInstance().getDisplayHeight()) {
            //ImageDownloadService.createImageToStorage(contestant.getPicture(), b, contestant.getCid(), new Dimension(Display.getInstance().getDisplayWidth() / 3, Display.getInstance().getDisplayHeight() / 2));
            final ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {

                    EncodedImage image = EncodedImage.create(input);
                    b.setIcon(image.scaled(Display.getInstance().getDisplayWidth() / 3, Display.getInstance().getDisplayHeight() / 2));

                }
            };


            final NetworkManager manager = NetworkManager.getInstance();


            //String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
            request.setUrl(contestant.getPicture());

            request.setFailSilently(true);//stops user from seeing error message on failure
            request.setPost(false);
            request.setDuplicateSupported(true);

            manager.start();
            manager.setTimeout(5000);
            manager.addToQueue(request);
        } else {
            //ImageDownloadService.createImageToStorage(contestant.getPicture(), b, contestant.getCid(), new Dimension(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight() / 2));
            final ConnectionRequest request = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {

                    EncodedImage image = EncodedImage.create(input);
                    b.setIcon(image.scaled(Display.getInstance().getDisplayWidth() / 2, Display.getInstance().getDisplayHeight() / 2));

                }
            };


            final NetworkManager manager = NetworkManager.getInstance();


            //String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
            request.setUrl(contestant.getPicture());

            request.setFailSilently(true);//stops user from seeing error message on failure
            request.setPost(false);
            request.setDuplicateSupported(true);

            manager.start();
            manager.setTimeout(5000);
            manager.addToQueue(request);
        }
    }

    @Override
    protected void onAppRegister_AppUserPhoneNumberTextFieldAction(Component c, ActionEvent event) {

        String country = findCountryComboBox(c.getComponentForm()).getSelectedItem().toString();
        if ((findAppUserPhoneNumberTextField(c.getComponentForm()).hasFocus()) && (("choose your country".equals(country)) || (("----Others".equals(country)) && ("".equals((String) findOtherCountryTextField(c.getComponentForm()).getText()))))) {
            Dialog.show("", "please choose your country first", "OK", null);
        }
    }

    @Override
    protected void onSelectedContestant_VoteSelectedContestantAction(Component c, ActionEvent event) {

        back();
    }
}
