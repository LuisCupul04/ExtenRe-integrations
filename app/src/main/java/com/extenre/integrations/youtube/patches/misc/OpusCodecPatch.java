package com.extenre.integrations.youtube.patches.misc;

import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class OpusCodecPatch {

    public static boolean enableOpusCodec() {
        return Settings.ENABLE_OPUS_CODEC.get();
    }
}
