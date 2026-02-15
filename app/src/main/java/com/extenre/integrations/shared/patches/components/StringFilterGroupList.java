/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.shared.patches.components;

import com.extenre.integrations.shared.utils.StringTrieSearch;

public final class StringFilterGroupList extends FilterGroupList<String, StringFilterGroup> {
    protected StringTrieSearch createSearchGraph() {
        return new StringTrieSearch();
    }
}
