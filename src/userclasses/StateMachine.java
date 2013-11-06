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
    private Object choiceNews;

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
                        if ((email.indexOf("@") < 0) || (email.indexOf(".", email.indexOf("@")) < 0)) {
                            Dialog.show("Email", "invalid email format", "OK", null);
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

                }
            } else {

                if ((email.indexOf("@") < 0) || (email.indexOf(".", email.indexOf("@")) < 0)) {
                    Dialog.show("Email", "invalid email format", "OK", null);
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
        Display.getInstance().lockOrientation(true);//lockOrientation(true);
        //Storage.getInstance().clearStorage();
        f.setScrollable(false);
        Image oluchiImage = fetchResourceFile().getImage("oluchi.png");
        findLabelButton(f).setIcon(oluchiImage.scaledWidth(Display.getInstance().getDisplayWidth() - 20));


        Command close = new Command("Exit") {
            @Override
            public void actionPerformed(ActionEvent evt) {

//                showForm("LoginPage", null);
                Display.getInstance().exitApplication();
            }
        };

        f.getMenuBar().addCommand(close);

        f.setBackCommand(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                if (Display.getInstance().getCurrent() instanceof Dialog) {
                    System.out.println("Yes");
                    ((Dialog) Display.getInstance().getCurrent()).dispose();
                }
            }
        });
        //Image scout = fetchResourceFile().getImage("scouting.jpg");
        Image contest = fetchResourceFile().getImage("contestants.jpg");
        Image newsB = fetchResourceFile().getImage("news_2.jpg");
        Image gallerypix = fetchResourceFile().getImage("gallery_2.jpg");
        Image antmTV = fetchResourceFile().getImage("ANTM_TV_ICON.jpg");
        Image partnerB = fetchResourceFile().getImage("partners_2.jpg");

        Vector<Hashtable> buttonLogos = new Vector<Hashtable>();

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

        Hashtable h6 = new Hashtable();
        h6.put("logo", antmTV);
        h6.put("name", "ANTMTV");

        //buttonLogos.add(h1);
        buttonLogos.add(h2);
        buttonLogos.add(h3);
        buttonLogos.add(h4);
        buttonLogos.add(h6);
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
        b.setIcon(i.scaledWidth(Display.getInstance().getDisplayWidth() / 6));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
                if ("News".equals(nm)) {
                    //Command cmds 

                    Button eti = new Button("Etisalat SweepStakes");
                    eti.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            choiceNews = "etisalat";
                            showForm("NewsView", null);
                        }
                    });
                    Button ant = new Button("ANTM News");
                    ant.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            choiceNews = "antm";
                            showForm("NewsView", null);
                        }
                    });

                    Command cmd = new Command("Cancel") {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                            ((Dialog) Display.getInstance().getCurrent()).dispose();
                        }
                    };

                    Dialog dlg = new Dialog();
                    dlg.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                    dlg.addComponent(ant);
                    dlg.addComponent(eti);
                    dlg.addCommand(cmd);

                    dlg.show();


                } else if ("Contestants".equals(nm)) {
                    showForm("ContestantsView", null);

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
                } else if ("ANTMTV".equals(nm)) {
                    showForm("VideoView", null);
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
        Image mtech = fetchResourceFile().getImage("mtechlogo.jpg");

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

        Hashtable h11 = new Hashtable();
        h11.put("logo", mtech);
        h11.put("name", "Mtech");
        
        partners.add(h6);
        partners.add(h2);
        partners.add(h3);
        partners.add(h4);
        partners.add(h5);
        partners.add(h9);
        partners.add(h11);
        partners.add(h1);
        partners.add(h10);
        partners.add(h8);        
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
        final BrowserComponent bcw = new BrowserComponent();

        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                bcw.destroy();
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

        f.setBackCommand(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                bcw.destroy();
                back();
            }
        });

        Image bgwhite = fetchResourceFile().getImage("WHITEBACKGROUND.png");

        Display.getInstance().getDisplayHeight();
        Display.getInstance().getDisplayWidth();

        f.getStyle().setBgImage(bgwhite);
        f.getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        findContainer1(f).removeAll();

        if ("etisalat".equals(choiceNews)) {
            bcw.setURL("https://m.facebook.com/etisalatng");
        } else if ("antm".equals(choiceNews)) {
            bcw.setURL("http://m.facebook.com/ANTMAFRICA");
        }

        Image arrRight = fetchResourceFile().getImage("arrowright32.png");
        Image arrLeft = fetchResourceFile().getImage("leftarrow32.png");

        Container con = new Container();
        Button b1 = new Button();
        b1.setUIID("Label");
        b1.setIcon(arrRight);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                try {
                    bcw.forward();
                } catch (Exception e) {
                    Dialog.show("", "No page in history", "OK", null);
                }

            }
        });

        Button b2 = new Button();
        b2.setUIID("Label");
        b2.setIcon(arrLeft);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (bcw.hasBack()) {
                    bcw.back();
                } else {
                    Dialog.show("", "No page in history", "OK", null);
                }
            }
        });
        con.setLayout(new BorderLayout());
        con.addComponent(BorderLayout.WEST, b2);
        con.addComponent(BorderLayout.EAST, b1);
        findContainer1(f).addComponent(BorderLayout.CENTER, bcw);
        f.addComponent(BorderLayout.SOUTH, con);


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


        Image ngr1 = fetchResourceFile().getImage("ngr_1.png");
        Image ngr2 = fetchResourceFile().getImage("ngr_2.png");
        Image ngr3 = fetchResourceFile().getImage("ngr_3.png");
        Image sa1 = fetchResourceFile().getImage("s_a.png");
        Image sa2 = fetchResourceFile().getImage("s_a_2.png");
        Image sa3 = fetchResourceFile().getImage("s_a_3.png");
        Image tunis = fetchResourceFile().getImage("tunisia.png");
        Image ug = fetchResourceFile().getImage("uganda.png");
        Image ang = fetchResourceFile().getImage("angola.png");
        Image gh = fetchResourceFile().getImage("ghana.png");
        Image ken = fetchResourceFile().getImage("kenya.png");
        Image mbic = fetchResourceFile().getImage("mo_bic.png");

        Hashtable h1 = new Hashtable();
        h1.put("image", ngr1);
        h1.put("name", "Omowunmi");

        Hashtable h2 = new Hashtable();
        h2.put("image", ngr2);
        h2.put("name", "Joyce");

        Hashtable h3 = new Hashtable();
        h3.put("image", ngr3);
        h3.put("name", "Opeyemi");

        Hashtable h4 = new Hashtable();
        h4.put("image", sa1);
        h4.put("name", "Michelle");

        Hashtable h5 = new Hashtable();
        h5.put("image", sa2);
        h5.put("name", "Cheandre");

        Hashtable h6 = new Hashtable();
        h6.put("image", sa3);
        h6.put("name", "Rhulani");

        Hashtable h7 = new Hashtable();
        h7.put("image", tunis);
        h7.put("name", "Marwa");

        Hashtable h8 = new Hashtable();
        h8.put("image", ug);
        h8.put("name", "Aamito");

        Hashtable h9 = new Hashtable();
        h9.put("image", ang);
        h9.put("name", "Michaela");

        Hashtable h10 = new Hashtable();
        h10.put("image", gh);
        h10.put("name", "Roselyn");

        Hashtable h11 = new Hashtable();
        h11.put("image", ken);
        h11.put("name", "Steffi");

        Hashtable h12 = new Hashtable();
        h12.put("image", mbic);
        h12.put("name", "Safira");

        Vector<Hashtable> conVec = new Vector<Hashtable>();
        conVec.add(h1);
        conVec.add(h2);
        conVec.add(h3);
        conVec.add(h4);
        conVec.add(h5);
        conVec.add(h6);
        conVec.add(h7);
        conVec.add(h8);
        conVec.add(h9);
        conVec.add(h10);
        conVec.add(h11);
        conVec.add(h12);

        for (int i = 0; i < conVec.size(); i++) {
            Hashtable hashtable = conVec.elementAt(i);
            findContainer(f).addComponent(addContestant((Image) hashtable.get("image"), (String) hashtable.get("name")));
            f.revalidate();
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

        super.processBackground(f);
        if ("Main".equals(f.getName())) {
            if (Storage.getInstance().exists("AntmUser")) {

                showForm("Menu", null);
                return false;

            }

        }
        return true;
    }

    public Container addContestant(Image pix, String name) {

        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachContestant");
        findContestantPicture(c).setIcon(pix.scaledWidth(Display.getInstance().getDisplayWidth() / 2));
        findContestantName(c).setText(name);
        //.setIcon(photo.scaledWidth(Display.getInstance().getDisplayWidth() / 3));

        return c;
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

        String num = Display.getInstance().getMsisdn();
        if (num != null) {
            findAppUserPhoneNumberTextField(f).setText(num);
        }
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
            findContainer(f).addComponent(addGalleryPix(hashtable.get("url").toString(), i, f));
        }
    }

    private Container addGalleryPix(final String imageurl, int i, final Form f) {
        Resources res = fetchResourceFile();
        Container c = createContainer(res, "EachGalleryPixZoom");

        final Button b = findGalleryImage(c);

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

    @Override
    protected void onAppRegister_AppUserPhoneNumberTextFieldAction(Component c, ActionEvent event) {

        String country = findCountryComboBox(c.getComponentForm()).getSelectedItem().toString();
        if ((findAppUserPhoneNumberTextField(c.getComponentForm()).hasFocus()) && (("choose your country".equals(country)) || (("----Others".equals(country)) && ("".equals((String) findOtherCountryTextField(c.getComponentForm()).getText()))))) {
            Dialog.show("", "please choose your country first", "OK", null);
        }
    }

    @Override
    protected void beforeVideoView(Form f) {
        //http://www.youtube.com/antmafrica
        final BrowserComponent bcw = new BrowserComponent();
        Image btnHOME = fetchResourceFile().getImage("home.png");
        Button bc = new Button();
        bc.setUIID("Label");
        bc.setIcon(btnHOME);
        bc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                bcw.destroy();
                showForm("Menu", null);
//                back();
            }
        });
        Image bghead = fetchResourceFile().getImage("head.png");
        findContainer1(f).getStyle().setBgImage(bghead);
        findContainer1(f).getStyle().setBorder(null);
        findContainer1(f).getStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED, true);
        findContainer1(f).addComponent(BorderLayout.WEST, bc);
        findLabel(f).setText("Videos");

        bcw.setURL("http://m.youtube.com/antmafrica");

        Image arrRight = fetchResourceFile().getImage("arrowright32.png");
        Image arrLeft = fetchResourceFile().getImage("leftarrow32.png");

        Container con = new Container();
        Button b1 = new Button();
        b1.setUIID("Label");
        b1.setIcon(arrRight);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if (bcw.hasForward()) {
                    bcw.forward();
                } else {
                    Dialog.show("", "No page in history", "OK", null);
                }

            }
        });

        Button b2 = new Button();
        b2.setUIID("Label");
        b2.setIcon(arrLeft);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (bcw.hasBack()) {
                    bcw.back();
                } else {
                    Dialog.show("", "No page in history", "OK", null);
                }
            }
        });
        con.setLayout(new BorderLayout());
        con.addComponent(BorderLayout.WEST, b2);
        con.addComponent(BorderLayout.EAST, b1);
        findContainer(f).addComponent(BorderLayout.CENTER, bcw);
        f.addComponent(BorderLayout.SOUTH, con);

        f.setBackCommand(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                bcw.destroy();
                back();
            }
        });
    }
}
