/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.video;

import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class HDRVideoPatch {

    public static boolean disableHDRVideo() {
        return !Settings.DISABLE_HDR_VIDEO.get();
    }
}
