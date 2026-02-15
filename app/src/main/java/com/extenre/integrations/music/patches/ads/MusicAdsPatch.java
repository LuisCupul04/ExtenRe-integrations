/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.music.patches.ads;

import com.extenre.integrations.music.settings.Settings;

@SuppressWarnings("unused")
public class MusicAdsPatch {

    public static boolean hideMusicAds() {
        return !Settings.HIDE_MUSIC_ADS.get();
    }

    public static boolean hideMusicAds(boolean original) {
        return !Settings.HIDE_MUSIC_ADS.get() && original;
    }
}
