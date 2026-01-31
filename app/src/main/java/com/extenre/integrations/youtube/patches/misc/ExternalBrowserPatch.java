package com.extenre.integrations.youtube.patches.misc;

import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class ExternalBrowserPatch {

    public static String enableExternalBrowser(final String original) {
        if (!Settings.ENABLE_EXTERNAL_BROWSER.get())
            return original;

        return "";
    }
}
