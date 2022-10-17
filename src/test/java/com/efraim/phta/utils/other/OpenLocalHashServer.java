package com.efraim.phta.utils.other;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.efraim.phta.utils.other.ResourceBundleUtil.getCurrentEnvironment;
import static com.efraim.phta.utils.other.ResourceBundleUtil.getResourceBundle;

public class OpenLocalHashServer {

    public static void openAPIFile() throws IOException
    {
        if(getCurrentEnvironment().equalsIgnoreCase("local")){
            File file = new File(getResourceBundle().getString("HASHSERVE_URI"));
            if(file.exists()){
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
        }
    }
}
