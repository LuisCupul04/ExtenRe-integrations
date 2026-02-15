/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.music.utils;

import static com.extenre.integrations.music.utils.ExtendedUtils.getDialogBuilder;
import static com.extenre.integrations.shared.utils.StringRef.str;
import static com.extenre.integrations.shared.utils.Utils.runOnMainThreadDelayed;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.Objects;

public class RestartUtils {

    public static void restartApp(@NonNull Activity activity) {
        final Intent intent = Objects.requireNonNull(activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName()));
        final Intent mainIntent = Intent.makeRestartActivityTask(intent.getComponent());

        activity.finishAffinity();
        activity.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }

    public static void showRestartDialog(@NonNull Activity activity) {
        showRestartDialog(activity, "extenre_extended_restart_message", 0);
    }

    public static void showRestartDialog(@NonNull Activity activity, @NonNull String message, long delay) {
        getDialogBuilder(activity)
                .setMessage(str(message))
                .setPositiveButton(android.R.string.ok, (dialog, id) -> runOnMainThreadDelayed(() -> restartApp(activity), delay))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }
}