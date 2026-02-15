/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

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

