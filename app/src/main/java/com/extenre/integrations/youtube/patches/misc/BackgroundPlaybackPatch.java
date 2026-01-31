package com.extenre.integrations.youtube.patches.misc;

import com.extenre.integrations.youtube.shared.ShortsPlayerState;

@SuppressWarnings("unused")
public class BackgroundPlaybackPatch {

    public static boolean allowBackgroundPlayback(boolean original) {
        return original || ShortsPlayerState.getCurrent().isClosed();
    }

}
