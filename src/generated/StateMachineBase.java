/**
 * This class contains generated code from the Codename One Designer, DO NOT MODIFY!
 * This class is designed for subclassing that way the code generator can overwrite it
 * anytime without erasing your changes which should exist in a subclass!
 * For details about this file and how it works please read this blog post:
 * http://codenameone.blogspot.com/2010/10/ui-builder-class-how-to-actually-use.html
*/
package generated;

import com.codename1.ui.*;
import com.codename1.ui.util.*;
import com.codename1.ui.plaf.*;
import java.util.Hashtable;
import com.codename1.ui.events.*;

public abstract class StateMachineBase extends UIBuilder {
    private Container aboutToShowThisContainer;
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    /**
    * @deprecated use the version that accepts a resource as an argument instead
    
**/
    protected void initVars() {}

    protected void initVars(Resources res) {}

    public StateMachineBase(Resources res, String resPath, boolean loadTheme) {
        startApp(res, resPath, loadTheme);
    }

    public Container startApp(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComboBox", com.codename1.ui.ComboBox.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    if(resPath.endsWith(".res")) {
                        res = Resources.open(resPath);
                        System.out.println("Warning: you should construct the state machine without the .res extension to allow theme overlays");
                    } else {
                        res = Resources.openLayered(resPath);
                    }
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        if(res != null) {
            setResourceFilePath(resPath);
            setResourceFile(res);
            initVars(res);
            return showForm(getFirstFormName(), null);
        } else {
            Form f = (Form)createContainer(resPath, getFirstFormName());
            initVars(fetchResourceFile());
            beforeShow(f);
            f.show();
            postShow(f);
            return f;
        }
    }

    protected String getFirstFormName() {
        return "Main";
    }

    public Container createWidget(Resources res, String resPath, boolean loadTheme) {
        initVars();
        UIBuilder.registerCustomComponent("Button", com.codename1.ui.Button.class);
        UIBuilder.registerCustomComponent("ComboBox", com.codename1.ui.ComboBox.class);
        UIBuilder.registerCustomComponent("Form", com.codename1.ui.Form.class);
        UIBuilder.registerCustomComponent("Label", com.codename1.ui.Label.class);
        UIBuilder.registerCustomComponent("TextField", com.codename1.ui.TextField.class);
        UIBuilder.registerCustomComponent("Container", com.codename1.ui.Container.class);
        if(loadTheme) {
            if(res == null) {
                try {
                    res = Resources.openLayered(resPath);
                } catch(java.io.IOException err) { err.printStackTrace(); }
            }
            initTheme(res);
        }
        return createContainer(resPath, "Main");
    }

    protected void initTheme(Resources res) {
            String[] themes = res.getThemeResourceNames();
            if(themes != null && themes.length > 0) {
                UIManager.getInstance().setThemeProps(res.getTheme(themes[0]));
            }
    }

    public StateMachineBase() {
    }

    public StateMachineBase(String resPath) {
        this(null, resPath, true);
    }

    public StateMachineBase(Resources res) {
        this(res, null, true);
    }

    public StateMachineBase(String resPath, boolean loadTheme) {
        this(null, resPath, loadTheme);
    }

    public StateMachineBase(Resources res, boolean loadTheme) {
        this(res, null, loadTheme);
    }

    public com.codename1.ui.Container findContainer4(Component root) {
        return (com.codename1.ui.Container)findByName("Container4", root);
    }

    public com.codename1.ui.Container findContainer4() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container4", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container4", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer3(Component root) {
        return (com.codename1.ui.Container)findByName("Container3", root);
    }

    public com.codename1.ui.Container findContainer3() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer2(Component root) {
        return (com.codename1.ui.Container)findByName("Container2", root);
    }

    public com.codename1.ui.Container findContainer2() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findPartnerLogo(Component root) {
        return (com.codename1.ui.Label)findByName("partnerLogo", root);
    }

    public com.codename1.ui.Label findPartnerLogo() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("partnerLogo", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("partnerLogo", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer1(Component root) {
        return (com.codename1.ui.Container)findByName("Container1", root);
    }

    public com.codename1.ui.Container findContainer1() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewUserUsernameTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newUserUsernameTextField", root);
    }

    public com.codename1.ui.TextField findNewUserUsernameTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newUserUsernameTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newUserUsernameTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer6(Component root) {
        return (com.codename1.ui.Container)findByName("Container6", root);
    }

    public com.codename1.ui.Container findContainer6() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container6", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container6", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSponsorLogo(Component root) {
        return (com.codename1.ui.Label)findByName("sponsorLogo", root);
    }

    public com.codename1.ui.Label findSponsorLogo() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("sponsorLogo", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("sponsorLogo", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer5(Component root) {
        return (com.codename1.ui.Container)findByName("Container5", root);
    }

    public com.codename1.ui.Container findContainer5() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container5", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container5", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.ComboBox findCountryComboBox(Component root) {
        return (com.codename1.ui.ComboBox)findByName("countryComboBox", root);
    }

    public com.codename1.ui.ComboBox findCountryComboBox() {
        com.codename1.ui.ComboBox cmp = (com.codename1.ui.ComboBox)findByName("countryComboBox", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.ComboBox)findByName("countryComboBox", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findOtherCountryTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("otherCountryTextField", root);
    }

    public com.codename1.ui.TextField findOtherCountryTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("otherCountryTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("otherCountryTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCancelCreate(Component root) {
        return (com.codename1.ui.Button)findByName("cancelCreate", root);
    }

    public com.codename1.ui.Button findCancelCreate() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("cancelCreate", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("cancelCreate", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findCreateAppUser(Component root) {
        return (com.codename1.ui.Button)findByName("createAppUser", root);
    }

    public com.codename1.ui.Button findCreateAppUser() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("createAppUser", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("createAppUser", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel1(Component root) {
        return (com.codename1.ui.Label)findByName("Label1", root);
    }

    public com.codename1.ui.Label findLabel1() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label1", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label1", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSplashLogo(Component root) {
        return (com.codename1.ui.Label)findByName("splashLogo", root);
    }

    public com.codename1.ui.Label findSplashLogo() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("splashLogo", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("splashLogo", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel3(Component root) {
        return (com.codename1.ui.Label)findByName("Label3", root);
    }

    public com.codename1.ui.Label findLabel3() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label3", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label3", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findEachPartner(Component root) {
        return (com.codename1.ui.Container)findByName("EachPartner", root);
    }

    public com.codename1.ui.Container findEachPartner() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("EachPartner", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("EachPartner", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel2(Component root) {
        return (com.codename1.ui.Label)findByName("Label2", root);
    }

    public com.codename1.ui.Label findLabel2() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label2", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label2", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findEachContestant(Component root) {
        return (com.codename1.ui.Container)findByName("EachContestant", root);
    }

    public com.codename1.ui.Container findEachContestant() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("EachContestant", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("EachContestant", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer(Component root) {
        return (com.codename1.ui.Container)findByName("Container", root);
    }

    public com.codename1.ui.Container findContainer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findContestantPicture(Component root) {
        return (com.codename1.ui.Button)findByName("contestantPicture", root);
    }

    public com.codename1.ui.Button findContestantPicture() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("contestantPicture", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("contestantPicture", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findLabelButton(Component root) {
        return (com.codename1.ui.Button)findByName("LabelButton", root);
    }

    public com.codename1.ui.Button findLabelButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("LabelButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("LabelButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findAnodaRenderer(Component root) {
        return (com.codename1.ui.Container)findByName("AnodaRenderer", root);
    }

    public com.codename1.ui.Container findAnodaRenderer() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("AnodaRenderer", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("AnodaRenderer", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findSelectedCountryCode(Component root) {
        return (com.codename1.ui.Label)findByName("selectedCountryCode", root);
    }

    public com.codename1.ui.Label findSelectedCountryCode() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("selectedCountryCode", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("selectedCountryCode", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findNewUserEmailTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("newUserEmailTextField", root);
    }

    public com.codename1.ui.TextField findNewUserEmailTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("newUserEmailTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("newUserEmailTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findContainer11(Component root) {
        return (com.codename1.ui.Container)findByName("Container11", root);
    }

    public com.codename1.ui.Container findContainer11() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("Container11", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("Container11", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findMenuButtonsPix(Component root) {
        return (com.codename1.ui.Container)findByName("MenuButtonsPix", root);
    }

    public com.codename1.ui.Container findMenuButtonsPix() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("MenuButtonsPix", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("MenuButtonsPix", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findMenuButton(Component root) {
        return (com.codename1.ui.Button)findByName("menuButton", root);
    }

    public com.codename1.ui.Button findMenuButton() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("menuButton", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("menuButton", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Button findGalleryImage(Component root) {
        return (com.codename1.ui.Button)findByName("galleryImage", root);
    }

    public com.codename1.ui.Button findGalleryImage() {
        com.codename1.ui.Button cmp = (com.codename1.ui.Button)findByName("galleryImage", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Button)findByName("galleryImage", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findContestantName(Component root) {
        return (com.codename1.ui.Label)findByName("contestantName", root);
    }

    public com.codename1.ui.Label findContestantName() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("contestantName", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("contestantName", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Label findLabel(Component root) {
        return (com.codename1.ui.Label)findByName("Label", root);
    }

    public com.codename1.ui.Label findLabel() {
        com.codename1.ui.Label cmp = (com.codename1.ui.Label)findByName("Label", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Label)findByName("Label", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.TextField findAppUserPhoneNumberTextField(Component root) {
        return (com.codename1.ui.TextField)findByName("appUserPhoneNumberTextField", root);
    }

    public com.codename1.ui.TextField findAppUserPhoneNumberTextField() {
        com.codename1.ui.TextField cmp = (com.codename1.ui.TextField)findByName("appUserPhoneNumberTextField", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.TextField)findByName("appUserPhoneNumberTextField", aboutToShowThisContainer);
        }
        return cmp;
    }

    public com.codename1.ui.Container findEachGalleryPixZoom(Component root) {
        return (com.codename1.ui.Container)findByName("EachGalleryPixZoom", root);
    }

    public com.codename1.ui.Container findEachGalleryPixZoom() {
        com.codename1.ui.Container cmp = (com.codename1.ui.Container)findByName("EachGalleryPixZoom", Display.getInstance().getCurrent());
        if(cmp == null && aboutToShowThisContainer != null) {
            cmp = (com.codename1.ui.Container)findByName("EachGalleryPixZoom", aboutToShowThisContainer);
        }
        return cmp;
    }

    protected void exitForm(Form f) {
        if("AppRegister".equals(f.getName())) {
            exitAppRegister(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachContestant".equals(f.getName())) {
            exitEachContestant(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContestantsView".equals(f.getName())) {
            exitContestantsView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewsView".equals(f.getName())) {
            exitNewsView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachPartner".equals(f.getName())) {
            exitEachPartner(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AnodaRenderer".equals(f.getName())) {
            exitAnodaRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("PartnersView".equals(f.getName())) {
            exitPartnersView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Menu".equals(f.getName())) {
            exitMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoView".equals(f.getName())) {
            exitVideoView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MenuButtonsPix".equals(f.getName())) {
            exitMenuButtonsPix(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gallery".equals(f.getName())) {
            exitGallery(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachGalleryPixZoom".equals(f.getName())) {
            exitEachGalleryPixZoom(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            exitMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void exitAppRegister(Form f) {
    }


    protected void exitEachContestant(Form f) {
    }


    protected void exitContestantsView(Form f) {
    }


    protected void exitNewsView(Form f) {
    }


    protected void exitEachPartner(Form f) {
    }


    protected void exitAnodaRenderer(Form f) {
    }


    protected void exitPartnersView(Form f) {
    }


    protected void exitMenu(Form f) {
    }


    protected void exitVideoView(Form f) {
    }


    protected void exitMenuButtonsPix(Form f) {
    }


    protected void exitGallery(Form f) {
    }


    protected void exitEachGalleryPixZoom(Form f) {
    }


    protected void exitMain(Form f) {
    }

    protected void beforeShow(Form f) {
    aboutToShowThisContainer = f;
        if("AppRegister".equals(f.getName())) {
            beforeAppRegister(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachContestant".equals(f.getName())) {
            beforeEachContestant(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContestantsView".equals(f.getName())) {
            beforeContestantsView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewsView".equals(f.getName())) {
            beforeNewsView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachPartner".equals(f.getName())) {
            beforeEachPartner(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AnodaRenderer".equals(f.getName())) {
            beforeAnodaRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("PartnersView".equals(f.getName())) {
            beforePartnersView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Menu".equals(f.getName())) {
            beforeMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoView".equals(f.getName())) {
            beforeVideoView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MenuButtonsPix".equals(f.getName())) {
            beforeMenuButtonsPix(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gallery".equals(f.getName())) {
            beforeGallery(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachGalleryPixZoom".equals(f.getName())) {
            beforeEachGalleryPixZoom(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            beforeMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeAppRegister(Form f) {
    }


    protected void beforeEachContestant(Form f) {
    }


    protected void beforeContestantsView(Form f) {
    }


    protected void beforeNewsView(Form f) {
    }


    protected void beforeEachPartner(Form f) {
    }


    protected void beforeAnodaRenderer(Form f) {
    }


    protected void beforePartnersView(Form f) {
    }


    protected void beforeMenu(Form f) {
    }


    protected void beforeVideoView(Form f) {
    }


    protected void beforeMenuButtonsPix(Form f) {
    }


    protected void beforeGallery(Form f) {
    }


    protected void beforeEachGalleryPixZoom(Form f) {
    }


    protected void beforeMain(Form f) {
    }

    protected void beforeShowContainer(Container c) {
        aboutToShowThisContainer = c;
        if("AppRegister".equals(c.getName())) {
            beforeContainerAppRegister(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachContestant".equals(c.getName())) {
            beforeContainerEachContestant(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContestantsView".equals(c.getName())) {
            beforeContainerContestantsView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewsView".equals(c.getName())) {
            beforeContainerNewsView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachPartner".equals(c.getName())) {
            beforeContainerEachPartner(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AnodaRenderer".equals(c.getName())) {
            beforeContainerAnodaRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("PartnersView".equals(c.getName())) {
            beforeContainerPartnersView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Menu".equals(c.getName())) {
            beforeContainerMenu(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoView".equals(c.getName())) {
            beforeContainerVideoView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MenuButtonsPix".equals(c.getName())) {
            beforeContainerMenuButtonsPix(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gallery".equals(c.getName())) {
            beforeContainerGallery(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachGalleryPixZoom".equals(c.getName())) {
            beforeContainerEachGalleryPixZoom(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            beforeContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void beforeContainerAppRegister(Container c) {
    }


    protected void beforeContainerEachContestant(Container c) {
    }


    protected void beforeContainerContestantsView(Container c) {
    }


    protected void beforeContainerNewsView(Container c) {
    }


    protected void beforeContainerEachPartner(Container c) {
    }


    protected void beforeContainerAnodaRenderer(Container c) {
    }


    protected void beforeContainerPartnersView(Container c) {
    }


    protected void beforeContainerMenu(Container c) {
    }


    protected void beforeContainerVideoView(Container c) {
    }


    protected void beforeContainerMenuButtonsPix(Container c) {
    }


    protected void beforeContainerGallery(Container c) {
    }


    protected void beforeContainerEachGalleryPixZoom(Container c) {
    }


    protected void beforeContainerMain(Container c) {
    }

    protected void postShow(Form f) {
        if("AppRegister".equals(f.getName())) {
            postAppRegister(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachContestant".equals(f.getName())) {
            postEachContestant(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContestantsView".equals(f.getName())) {
            postContestantsView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewsView".equals(f.getName())) {
            postNewsView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachPartner".equals(f.getName())) {
            postEachPartner(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("AnodaRenderer".equals(f.getName())) {
            postAnodaRenderer(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("PartnersView".equals(f.getName())) {
            postPartnersView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Menu".equals(f.getName())) {
            postMenu(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoView".equals(f.getName())) {
            postVideoView(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("MenuButtonsPix".equals(f.getName())) {
            postMenuButtonsPix(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gallery".equals(f.getName())) {
            postGallery(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachGalleryPixZoom".equals(f.getName())) {
            postEachGalleryPixZoom(f);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            postMain(f);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postAppRegister(Form f) {
    }


    protected void postEachContestant(Form f) {
    }


    protected void postContestantsView(Form f) {
    }


    protected void postNewsView(Form f) {
    }


    protected void postEachPartner(Form f) {
    }


    protected void postAnodaRenderer(Form f) {
    }


    protected void postPartnersView(Form f) {
    }


    protected void postMenu(Form f) {
    }


    protected void postVideoView(Form f) {
    }


    protected void postMenuButtonsPix(Form f) {
    }


    protected void postGallery(Form f) {
    }


    protected void postEachGalleryPixZoom(Form f) {
    }


    protected void postMain(Form f) {
    }

    protected void postShowContainer(Container c) {
        if("AppRegister".equals(c.getName())) {
            postContainerAppRegister(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachContestant".equals(c.getName())) {
            postContainerEachContestant(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContestantsView".equals(c.getName())) {
            postContainerContestantsView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewsView".equals(c.getName())) {
            postContainerNewsView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachPartner".equals(c.getName())) {
            postContainerEachPartner(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("AnodaRenderer".equals(c.getName())) {
            postContainerAnodaRenderer(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("PartnersView".equals(c.getName())) {
            postContainerPartnersView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Menu".equals(c.getName())) {
            postContainerMenu(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoView".equals(c.getName())) {
            postContainerVideoView(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("MenuButtonsPix".equals(c.getName())) {
            postContainerMenuButtonsPix(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gallery".equals(c.getName())) {
            postContainerGallery(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachGalleryPixZoom".equals(c.getName())) {
            postContainerEachGalleryPixZoom(c);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(c.getName())) {
            postContainerMain(c);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void postContainerAppRegister(Container c) {
    }


    protected void postContainerEachContestant(Container c) {
    }


    protected void postContainerContestantsView(Container c) {
    }


    protected void postContainerNewsView(Container c) {
    }


    protected void postContainerEachPartner(Container c) {
    }


    protected void postContainerAnodaRenderer(Container c) {
    }


    protected void postContainerPartnersView(Container c) {
    }


    protected void postContainerMenu(Container c) {
    }


    protected void postContainerVideoView(Container c) {
    }


    protected void postContainerMenuButtonsPix(Container c) {
    }


    protected void postContainerGallery(Container c) {
    }


    protected void postContainerEachGalleryPixZoom(Container c) {
    }


    protected void postContainerMain(Container c) {
    }

    protected void onCreateRoot(String rootName) {
        if("AppRegister".equals(rootName)) {
            onCreateAppRegister();
            aboutToShowThisContainer = null;
            return;
        }

        if("EachContestant".equals(rootName)) {
            onCreateEachContestant();
            aboutToShowThisContainer = null;
            return;
        }

        if("ContestantsView".equals(rootName)) {
            onCreateContestantsView();
            aboutToShowThisContainer = null;
            return;
        }

        if("NewsView".equals(rootName)) {
            onCreateNewsView();
            aboutToShowThisContainer = null;
            return;
        }

        if("EachPartner".equals(rootName)) {
            onCreateEachPartner();
            aboutToShowThisContainer = null;
            return;
        }

        if("AnodaRenderer".equals(rootName)) {
            onCreateAnodaRenderer();
            aboutToShowThisContainer = null;
            return;
        }

        if("PartnersView".equals(rootName)) {
            onCreatePartnersView();
            aboutToShowThisContainer = null;
            return;
        }

        if("Menu".equals(rootName)) {
            onCreateMenu();
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoView".equals(rootName)) {
            onCreateVideoView();
            aboutToShowThisContainer = null;
            return;
        }

        if("MenuButtonsPix".equals(rootName)) {
            onCreateMenuButtonsPix();
            aboutToShowThisContainer = null;
            return;
        }

        if("Gallery".equals(rootName)) {
            onCreateGallery();
            aboutToShowThisContainer = null;
            return;
        }

        if("EachGalleryPixZoom".equals(rootName)) {
            onCreateEachGalleryPixZoom();
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(rootName)) {
            onCreateMain();
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void onCreateAppRegister() {
    }


    protected void onCreateEachContestant() {
    }


    protected void onCreateContestantsView() {
    }


    protected void onCreateNewsView() {
    }


    protected void onCreateEachPartner() {
    }


    protected void onCreateAnodaRenderer() {
    }


    protected void onCreatePartnersView() {
    }


    protected void onCreateMenu() {
    }


    protected void onCreateVideoView() {
    }


    protected void onCreateMenuButtonsPix() {
    }


    protected void onCreateGallery() {
    }


    protected void onCreateEachGalleryPixZoom() {
    }


    protected void onCreateMain() {
    }

    protected Hashtable getFormState(Form f) {
        Hashtable h = super.getFormState(f);
        if("AppRegister".equals(f.getName())) {
            getStateAppRegister(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("EachContestant".equals(f.getName())) {
            getStateEachContestant(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("ContestantsView".equals(f.getName())) {
            getStateContestantsView(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("NewsView".equals(f.getName())) {
            getStateNewsView(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("EachPartner".equals(f.getName())) {
            getStateEachPartner(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("AnodaRenderer".equals(f.getName())) {
            getStateAnodaRenderer(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("PartnersView".equals(f.getName())) {
            getStatePartnersView(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Menu".equals(f.getName())) {
            getStateMenu(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("VideoView".equals(f.getName())) {
            getStateVideoView(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("MenuButtonsPix".equals(f.getName())) {
            getStateMenuButtonsPix(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Gallery".equals(f.getName())) {
            getStateGallery(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("EachGalleryPixZoom".equals(f.getName())) {
            getStateEachGalleryPixZoom(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

        if("Main".equals(f.getName())) {
            getStateMain(f, h);
            aboutToShowThisContainer = null;
            return h;
        }

            return h;
    }


    protected void getStateAppRegister(Form f, Hashtable h) {
    }


    protected void getStateEachContestant(Form f, Hashtable h) {
    }


    protected void getStateContestantsView(Form f, Hashtable h) {
    }


    protected void getStateNewsView(Form f, Hashtable h) {
    }


    protected void getStateEachPartner(Form f, Hashtable h) {
    }


    protected void getStateAnodaRenderer(Form f, Hashtable h) {
    }


    protected void getStatePartnersView(Form f, Hashtable h) {
    }


    protected void getStateMenu(Form f, Hashtable h) {
    }


    protected void getStateVideoView(Form f, Hashtable h) {
    }


    protected void getStateMenuButtonsPix(Form f, Hashtable h) {
    }


    protected void getStateGallery(Form f, Hashtable h) {
    }


    protected void getStateEachGalleryPixZoom(Form f, Hashtable h) {
    }


    protected void getStateMain(Form f, Hashtable h) {
    }

    protected void setFormState(Form f, Hashtable state) {
        super.setFormState(f, state);
        if("AppRegister".equals(f.getName())) {
            setStateAppRegister(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachContestant".equals(f.getName())) {
            setStateEachContestant(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("ContestantsView".equals(f.getName())) {
            setStateContestantsView(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("NewsView".equals(f.getName())) {
            setStateNewsView(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachPartner".equals(f.getName())) {
            setStateEachPartner(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("AnodaRenderer".equals(f.getName())) {
            setStateAnodaRenderer(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("PartnersView".equals(f.getName())) {
            setStatePartnersView(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Menu".equals(f.getName())) {
            setStateMenu(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("VideoView".equals(f.getName())) {
            setStateVideoView(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("MenuButtonsPix".equals(f.getName())) {
            setStateMenuButtonsPix(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Gallery".equals(f.getName())) {
            setStateGallery(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("EachGalleryPixZoom".equals(f.getName())) {
            setStateEachGalleryPixZoom(f, state);
            aboutToShowThisContainer = null;
            return;
        }

        if("Main".equals(f.getName())) {
            setStateMain(f, state);
            aboutToShowThisContainer = null;
            return;
        }

            return;
    }


    protected void setStateAppRegister(Form f, Hashtable state) {
    }


    protected void setStateEachContestant(Form f, Hashtable state) {
    }


    protected void setStateContestantsView(Form f, Hashtable state) {
    }


    protected void setStateNewsView(Form f, Hashtable state) {
    }


    protected void setStateEachPartner(Form f, Hashtable state) {
    }


    protected void setStateAnodaRenderer(Form f, Hashtable state) {
    }


    protected void setStatePartnersView(Form f, Hashtable state) {
    }


    protected void setStateMenu(Form f, Hashtable state) {
    }


    protected void setStateVideoView(Form f, Hashtable state) {
    }


    protected void setStateMenuButtonsPix(Form f, Hashtable state) {
    }


    protected void setStateGallery(Form f, Hashtable state) {
    }


    protected void setStateEachGalleryPixZoom(Form f, Hashtable state) {
    }


    protected void setStateMain(Form f, Hashtable state) {
    }

    protected boolean setListModel(List cmp) {
        String listName = cmp.getName();
        if("countryComboBox".equals(listName)) {
            return initListModelCountryComboBox(cmp);
        }
        return super.setListModel(cmp);
    }

    protected boolean initListModelCountryComboBox(List cmp) {
        return false;
    }

    protected void handleComponentAction(Component c, ActionEvent event) {
        Container rootContainerAncestor = getRootAncestor(c);
        if(rootContainerAncestor == null) return;
        String rootContainerName = rootContainerAncestor.getName();
        Container leadParentContainer = c.getParent().getLeadParent();
        if(leadParentContainer != null && leadParentContainer.getClass() != Container.class) {
            c = c.getParent().getLeadParent();
        }
        if(rootContainerName == null) return;
        if(rootContainerName.equals("AppRegister")) {
            if("newUserUsernameTextField".equals(c.getName())) {
                onAppRegister_NewUserUsernameTextFieldAction(c, event);
                return;
            }
            if("newUserEmailTextField".equals(c.getName())) {
                onAppRegister_NewUserEmailTextFieldAction(c, event);
                return;
            }
            if("countryComboBox".equals(c.getName())) {
                onAppRegister_CountryComboBoxAction(c, event);
                return;
            }
            if("otherCountryTextField".equals(c.getName())) {
                onAppRegister_OtherCountryTextFieldAction(c, event);
                return;
            }
            if("appUserPhoneNumberTextField".equals(c.getName())) {
                onAppRegister_AppUserPhoneNumberTextFieldAction(c, event);
                return;
            }
            if("createAppUser".equals(c.getName())) {
                onAppRegister_CreateAppUserAction(c, event);
                return;
            }
            if("cancelCreate".equals(c.getName())) {
                onAppRegister_CancelCreateAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("EachContestant")) {
            if("contestantPicture".equals(c.getName())) {
                onEachContestant_ContestantPictureAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("AnodaRenderer")) {
            if("galleryImage".equals(c.getName())) {
                onAnodaRenderer_GalleryImageAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("Menu")) {
            if("LabelButton".equals(c.getName())) {
                onMenu_LabelButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("MenuButtonsPix")) {
            if("menuButton".equals(c.getName())) {
                onMenuButtonsPix_MenuButtonAction(c, event);
                return;
            }
        }
        if(rootContainerName.equals("EachGalleryPixZoom")) {
            if("galleryImage".equals(c.getName())) {
                onEachGalleryPixZoom_GalleryImageAction(c, event);
                return;
            }
        }
    }

      protected void onAppRegister_NewUserUsernameTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onAppRegister_NewUserEmailTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onAppRegister_CountryComboBoxAction(Component c, ActionEvent event) {
      }

      protected void onAppRegister_OtherCountryTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onAppRegister_AppUserPhoneNumberTextFieldAction(Component c, ActionEvent event) {
      }

      protected void onAppRegister_CreateAppUserAction(Component c, ActionEvent event) {
      }

      protected void onAppRegister_CancelCreateAction(Component c, ActionEvent event) {
      }

      protected void onEachContestant_ContestantPictureAction(Component c, ActionEvent event) {
      }

      protected void onAnodaRenderer_GalleryImageAction(Component c, ActionEvent event) {
      }

      protected void onMenu_LabelButtonAction(Component c, ActionEvent event) {
      }

      protected void onMenuButtonsPix_MenuButtonAction(Component c, ActionEvent event) {
      }

      protected void onEachGalleryPixZoom_GalleryImageAction(Component c, ActionEvent event) {
      }

}
