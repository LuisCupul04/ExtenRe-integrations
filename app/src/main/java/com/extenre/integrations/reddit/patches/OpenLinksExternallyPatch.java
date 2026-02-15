/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.reddit.patches;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.lang.ref.WeakReference;

import com.extenre.integrations.reddit.settings.Settings;
import com.extenre.integrations.shared.utils.Logger;
import com.extenre.integrations.shared.utils.Utils;

@SuppressWarnings("unused")
public class OpenLinksExternallyPatch {
    private static WeakReference<Activity> activityRef = new WeakReference<>(null);

    /**
     * Override 'CustomTabsIntent', in order to open links in the default browser.
     * Instead of doing CustomTabsActivity,
     *
     * @param activity The activity, to start an Intent.
     * @param uri      The URL to be opened in the default browser.
     */
    public static boolean openLinksExternally(Activity activity, Uri uri) {
        try {
            if (activity != null && uri != null && Settings.OPEN_LINKS_EXTERNALLY.get()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                activity.startActivity(intent);
                return true;
            }
        } catch (Exception e) {
            Logger.printException(() -> "Can not open URL: " + uri, e);
        }
        return false;
    }

    public static void openLinksExternally(String uri) {
        if (uri != null && Settings.OPEN_LINKS_EXTERNALLY.get()) {
            Activity activity = activityRef.get();
            if (activity != null && !activity.isDestroyed()) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uri));
                    activity.startActivity(intent);
                    Utils.runOnMainThreadDelayed(() -> {
                        activity.finish();
                        activityRef = new WeakReference<>(null);
                    }, 100);
                } catch (Exception e) {
                    Logger.printException(() -> "Can not open URL: " + uri, e);
                }
            }
        }
    }

    public static void setActivity(Activity activity) {
        activityRef = new WeakReference<>(activity);
    }
}
