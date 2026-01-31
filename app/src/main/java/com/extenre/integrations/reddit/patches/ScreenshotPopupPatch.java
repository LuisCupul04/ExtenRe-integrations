package com.extenre.integrations.reddit.patches;

import com.extenre.integrations.reddit.settings.Settings;

@SuppressWarnings("unused")
public class ScreenshotPopupPatch {

    public static Boolean disableScreenshotPopup(Boolean original) {
        return Settings.DISABLE_SCREENSHOT_POPUP.get() ? Boolean.FALSE : original;
    }
}
