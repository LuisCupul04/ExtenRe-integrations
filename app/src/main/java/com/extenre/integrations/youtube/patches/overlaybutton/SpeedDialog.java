/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.overlaybutton;

import static com.extenre.integrations.shared.utils.StringRef.str;
import static com.extenre.integrations.shared.utils.Utils.showToastShort;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.extenre.integrations.shared.utils.Logger;
import com.extenre.integrations.youtube.settings.Settings;
import com.extenre.integrations.youtube.shared.VideoInformation;
import com.extenre.integrations.youtube.utils.VideoUtils;

@SuppressWarnings("unused")
public class SpeedDialog extends BottomControlButton {
    @Nullable
    private static SpeedDialog instance;

    public SpeedDialog(ViewGroup bottomControlsViewGroup) {
        super(
                bottomControlsViewGroup,
                "speed_dialog_button",
                Settings.OVERLAY_BUTTON_SPEED_DIALOG,
                view -> VideoUtils.showPlaybackSpeedDialog(view.getContext()),
                view -> {
                    if (!Settings.REMEMBER_PLAYBACK_SPEED_LAST_SELECTED.get() ||
                            VideoInformation.getPlaybackSpeed() == Settings.DEFAULT_PLAYBACK_SPEED.get()) {
                        VideoInformation.overridePlaybackSpeed(1.0f);
                        showToastShort(str("extenre_overlay_button_speed_dialog_reset", "1.0"));
                    } else {
                        float defaultSpeed = Settings.DEFAULT_PLAYBACK_SPEED.get();
                        VideoInformation.overridePlaybackSpeed(defaultSpeed);
                        showToastShort(str("extenre_overlay_button_speed_dialog_reset", defaultSpeed));
                    }

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
                instance = new SpeedDialog(viewGroup);
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