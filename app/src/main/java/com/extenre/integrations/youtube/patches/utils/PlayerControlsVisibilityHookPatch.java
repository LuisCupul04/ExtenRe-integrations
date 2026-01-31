package com.extenre.integrations.youtube.patches.utils;

import androidx.annotation.Nullable;

import com.extenre.integrations.youtube.shared.PlayerControlsVisibility;

@SuppressWarnings("unused")
public class PlayerControlsVisibilityHookPatch {
    /**
     * Injection point.
     */
    public static void setPlayerControlsVisibility(@Nullable Enum<?> youTubePlayerControlsVisibility) {
        if (youTubePlayerControlsVisibility == null) return;

        PlayerControlsVisibility.setFromString(youTubePlayerControlsVisibility.name());
    }
}

