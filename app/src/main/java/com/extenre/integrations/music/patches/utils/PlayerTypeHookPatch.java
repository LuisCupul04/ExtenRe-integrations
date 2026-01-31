package com.extenre.integrations.music.patches.utils;

import androidx.annotation.Nullable;

import com.extenre.integrations.music.shared.PlayerType;

@SuppressWarnings("unused")
public class PlayerTypeHookPatch {
    /**
     * Injection point.
     */
    public static void setPlayerType(@Nullable Enum<?> musicPlayerType) {
        if (musicPlayerType == null)
            return;

        PlayerType.setFromString(musicPlayerType.name());
    }
}

