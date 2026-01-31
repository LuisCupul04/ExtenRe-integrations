package com.extenre.integrations.music.patches.misc;

import com.extenre.integrations.music.settings.Settings;

@SuppressWarnings("unused")
public class OpusCodecPatch {

    public static boolean enableOpusCodec() {
        return Settings.ENABLE_OPUS_CODEC.get();
    }
}
