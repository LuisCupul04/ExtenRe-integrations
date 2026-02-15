/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.reddit.settings.preference.categories;

import android.content.Context;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

@SuppressWarnings("deprecation")
public abstract class ConditionalPreferenceCategory extends PreferenceCategory {
    public ConditionalPreferenceCategory(Context context, PreferenceScreen screen) {
        super(context);

        if (getSettingsStatus()) {
            screen.addPreference(this);
            addPreferences(context);
        }
    }

    public abstract boolean getSettingsStatus();

    public abstract void addPreferences(Context context);
}

