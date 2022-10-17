package com.efraim.phta.utils.other;

import java.util.ResourceBundle;

public class ResourceBundleUtil {

    private static String resourcePathFolder = "environment/";
    private static String resourceBaseName = "";
    public static ResourceBundle getResourceBundle(){
        String environmentSysProperty = System.getProperty("environment");
        if(environmentSysProperty == null){
            resourceBaseName = "local";
        }
        else{
            resourceBaseName = environmentSysProperty;
        }
        return ResourceBundle.getBundle(resourcePathFolder + resourceBaseName);
    }

    public static String getCurrentEnvironment(){
        return resourceBaseName;
    }
}
