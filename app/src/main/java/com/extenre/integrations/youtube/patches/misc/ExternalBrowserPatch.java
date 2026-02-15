/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.misc;

import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class ExternalBrowserPatch {

    public static String enableExternalBrowser(final String original) {
        if (!Settings.ENABLE_EXTERNAL_BROWSER.get())
            return original;

        return "";
    }
}
