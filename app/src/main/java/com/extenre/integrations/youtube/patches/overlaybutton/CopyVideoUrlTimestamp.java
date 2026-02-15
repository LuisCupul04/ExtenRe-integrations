/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.overlaybutton;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.extenre.integrations.shared.utils.Logger;
import com.extenre.integrations.youtube.settings.Settings;
import com.extenre.integrations.youtube.utils.VideoUtils;

@SuppressWarnings("unused")
public class CopyVideoUrlTimestamp extends BottomControlButton {
    @Nullable
    private static CopyVideoUrlTimestamp instance;

    public CopyVideoUrlTimestamp(ViewGroup bottomControlsViewGroup) {
        super(
                bottomControlsViewGroup,
                "copy_video_url_timestamp_button",
                Settings.OVERLAY_BUTTON_COPY_VIDEO_URL_TIMESTAMP,
                view -> VideoUtils.copyUrl(true),
                view -> {
                    VideoUtils.copyTimeStamp();
                    return true;
                }
        );
    }

    /**
     * Injection point.
     */
    public static void initialize(View bottomControlsViewGroup) {
        try {
            if (bottomControlsViewGroup instanceof ViewGroup viewGroup) {
                instance = new CopyVideoUrlTimestamp(viewGroup);
            }
        } catch (Exception ex) {
            Logger.printException(() -> "initialize failure", ex);
        }
    }

    /**
     * Injection point.
     */
    public static void changeVisibility(boolean showing, boolean animation) {
        if (instance != null) instance.setVisibility(showing, animation);
    }

    public static void changeVisibilityNegatedImmediate() {
        if (instance != null) instance.setVisibilityNegatedImmediate();
    }


}