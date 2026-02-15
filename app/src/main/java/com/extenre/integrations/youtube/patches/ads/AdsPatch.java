/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.ads;

import static com.extenre.integrations.shared.utils.Utils.hideViewBy0dpUnderCondition;

import android.view.View;

import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class AdsPatch {
    private static final boolean hideGeneralAdsEnabled = Settings.HIDE_GENERAL_ADS.get();
    private static final boolean hideGetPremiumAdsEnabled = Settings.HIDE_GET_PREMIUM.get();
    private static final boolean hideVideoAdsEnabled = Settings.HIDE_VIDEO_ADS.get();

    /**
     * Injection point.
     * Hide the view, which shows ads in the homepage.
     *
     * @param view The view, which shows ads.
     */
    public static void hideAdAttributionView(View view) {
        hideViewBy0dpUnderCondition(hideGeneralAdsEnabled, view);
    }

    public static boolean hideGetPremium() {
        return hideGetPremiumAdsEnabled;
    }

    /**
     * Injection point.
     */
    public static boolean hideVideoAds() {
        return !hideVideoAdsEnabled;
    }

    /**
     * Injection point.
     * <p>
     * Only used by old clients.
     * It is presumed to have been deprecated, and if it is confirmed that it is no longer used, remove it.
     */
    public static boolean hideVideoAds(boolean original) {
        return !hideVideoAdsEnabled && original;
    }

}
