package com.extenre.integrations.youtube.patches.video;

import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class HDRVideoPatch {

    public static boolean disableHDRVideo() {
        return !Settings.DISABLE_HDR_VIDEO.get();
    }
}
