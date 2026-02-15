/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.music.patches.misc;

import com.extenre.integrations.music.settings.Settings;

@SuppressWarnings("unused")
public class CairoSplashAnimationPatch {

    public static boolean disableCairoSplashAnimation(boolean original) {
        return !Settings.DISABLE_CAIRO_SPLASH_ANIMATION.get() && original;
    }
}
