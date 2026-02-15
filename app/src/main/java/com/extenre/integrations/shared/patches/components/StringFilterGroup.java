/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.shared.patches.components;

import com.extenre.integrations.shared.settings.BooleanSetting;

public class StringFilterGroup extends FilterGroup<String> {

    public StringFilterGroup(final BooleanSetting setting, final String... filters) {
        super(setting, filters);
    }

    @Override
    public FilterGroupResult check(final String string) {
        int matchedIndex = -1;
        int matchedLength = 0;
        if (isEnabled()) {
            for (String pattern : filters) {
                if (!string.isEmpty()) {
                    final int indexOf = string.indexOf(pattern);
                    if (indexOf >= 0) {
                        matchedIndex = indexOf;
                        matchedLength = pattern.length();
                        break;
                    }
                }
            }
        }
        return new FilterGroupResult(setting, matchedIndex, matchedLength);
    }
}
