package com.extenre.integrations.shared.patches.components;

import com.extenre.integrations.shared.utils.StringTrieSearch;

public final class StringFilterGroupList extends FilterGroupList<String, StringFilterGroup> {
    protected StringTrieSearch createSearchGraph() {
        return new StringTrieSearch();
    }
}
