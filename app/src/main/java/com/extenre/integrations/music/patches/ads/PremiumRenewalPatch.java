/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.music.patches.ads;

import static com.extenre.integrations.shared.utils.StringRef.str;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.extenre.integrations.music.settings.Settings;
import com.extenre.integrations.shared.utils.Logger;
import com.extenre.integrations.shared.utils.Utils;

@SuppressWarnings("unused")
public class PremiumRenewalPatch {

    public static void hidePremiumRenewal(LinearLayout buttonContainerView) {
        if (!Settings.HIDE_PREMIUM_RENEWAL.get())
            return;

        buttonContainerView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            try {
                Utils.runOnMainThreadDelayed(() -> {
                            if (!(buttonContainerView.getChildAt(0) instanceof ViewGroup closeButtonParentView))
                                return;
                            if (!(closeButtonParentView.getChildAt(0) instanceof TextView closeButtonView))
                                return;
                            if (closeButtonView.getText().toString().equals(str("dialog_got_it_text")))
                                Utils.clickView(closeButtonView);
                            else
                                Utils.hideViewByLayoutParams((View) buttonContainerView.getParent());
                        }, 0
                );
            } catch (Exception ex) {
                Logger.printException(() -> "hidePremiumRenewal failure", ex);
            }
        });
    }
}
