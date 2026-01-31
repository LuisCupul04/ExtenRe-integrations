package com.extenre.integrations.reddit.patches;

import com.extenre.integrations.reddit.settings.Settings;

@SuppressWarnings("unused")
public final class RecommendedCommunitiesPatch {

    public static boolean hideRecommendedCommunitiesShelf() {
        return Settings.HIDE_RECOMMENDED_COMMUNITIES_SHELF.get();
    }

}
