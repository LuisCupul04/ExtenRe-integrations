/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.shared.patches.spans;

import androidx.annotation.NonNull;

public enum SpanType {
    CLICKABLE("ClickableSpan"),
    FOREGROUND_COLOR("ForegroundColorSpan"),
    ABSOLUTE_SIZE("AbsoluteSizeSpan"),
    TYPEFACE("TypefaceSpan"),
    IMAGE("ImageSpan"),
    CUSTOM_CHARACTER_STYLE("CustomCharacterStyle"),
    UNKNOWN("Unknown");

    @NonNull
    public final String type;

    SpanType(@NonNull String type) {
        this.type = type;
    }
}
