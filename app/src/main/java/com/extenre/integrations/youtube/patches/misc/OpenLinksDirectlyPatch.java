/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.misc;

import android.net.Uri;

import java.util.Objects;

import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class OpenLinksDirectlyPatch {
    private static final String YOUTUBE_REDIRECT_PATH = "/redirect";

    public static Uri enableBypassRedirect(String uri) {
        final Uri parsed = Uri.parse(uri);
        if (!Settings.ENABLE_OPEN_LINKS_DIRECTLY.get())
            return parsed;

        if (Objects.equals(parsed.getPath(), YOUTUBE_REDIRECT_PATH)) {
            return Uri.parse(Uri.decode(parsed.getQueryParameter("q")));
        }

        return parsed;
    }
}
