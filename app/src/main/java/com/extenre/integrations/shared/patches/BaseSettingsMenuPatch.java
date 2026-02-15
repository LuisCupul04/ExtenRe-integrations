/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.shared.patches;

import android.util.Log;

import androidx.preference.PreferenceScreen;

@SuppressWarnings("unused")
public class BaseSettingsMenuPatch {

    /**
     * Rest of the implementation added by patch.
     */
    public static void removePreference(PreferenceScreen mPreferenceScreen, String key) {
        Log.d("Extended: SettingsMenuPatch", "key: " + key);
    }
}