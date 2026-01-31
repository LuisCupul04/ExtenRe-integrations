package com.extenre.integrations.music.patches.misc;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.extenre.integrations.music.patches.components.ShareSheetMenuFilter;
import com.extenre.integrations.music.settings.Settings;
import com.extenre.integrations.shared.utils.Logger;
import com.extenre.integrations.shared.utils.Utils;

@SuppressWarnings("unused")
public class ShareSheetPatch {
    /**
     * Injection point.
     */
    public static void onShareSheetMenuCreate(final RecyclerView recyclerView) {
        if (!Settings.CHANGE_SHARE_SHEET.get())
            return;

        recyclerView.getViewTreeObserver().addOnDrawListener(() -> {
            try {
                if (!ShareSheetMenuFilter.isShareSheetMenuVisible)
                    return;
                if (!(recyclerView.getChildAt(0) instanceof ViewGroup shareContainer)) {
                    return;
                }
                if (!(shareContainer.getChildAt(shareContainer.getChildCount() - 1) instanceof ViewGroup shareWithOtherAppsView)) {
                    return;
                }
                ShareSheetMenuFilter.isShareSheetMenuVisible = false;

                recyclerView.setVisibility(View.GONE);
                Utils.clickView(shareWithOtherAppsView);
            } catch (Exception ex) {
                Logger.printException(() -> "onShareSheetMenuCreate failure", ex);
            }
        });
    }

}
