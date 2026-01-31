package com.extenre.integrations.reddit.patches;

import com.extenre.integrations.reddit.settings.Settings;

@SuppressWarnings("unused")
public final class TrendingTodayShelfPatch {

    public static boolean hideTrendingTodayShelf() {
        return Settings.HIDE_TRENDING_TODAY_SHELF.get();
    }

    public static String removeTrendingLabel(String label) {
        return Settings.HIDE_TRENDING_TODAY_SHELF.get() &&
                label != null &&
                label.startsWith("Trending")
                ? ""
                : label;
    }

}
