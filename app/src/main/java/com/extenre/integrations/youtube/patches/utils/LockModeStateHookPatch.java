package com.extenre.integrations.youtube.patches.utils;

import androidx.annotation.Nullable;

import com.extenre.integrations.youtube.shared.LockModeState;

@SuppressWarnings("unused")
public class LockModeStateHookPatch {
    /**
     * Injection point.
     */
    public static void setLockModeState(@Nullable Enum<?> lockModeState) {
        if (lockModeState == null) return;

        LockModeState.setFromString(lockModeState.name());
    }
}

