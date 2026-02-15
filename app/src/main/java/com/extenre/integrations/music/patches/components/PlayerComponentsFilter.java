/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.music.patches.components;

import com.extenre.integrations.music.settings.Settings;
import com.extenre.integrations.shared.patches.components.Filter;
import com.extenre.integrations.shared.patches.components.StringFilterGroup;

@SuppressWarnings("unused")
public final class PlayerComponentsFilter extends Filter {

    public PlayerComponentsFilter() {
        addIdentifierCallbacks(
                new StringFilterGroup(
                        Settings.HIDE_COMMENT_CHANNEL_GUIDELINES,
                        "channel_guidelines_entry_banner.eml",
                        "community_guidelines.eml"
                )
        );
        addPathCallbacks(
                new StringFilterGroup(
                        Settings.HIDE_COMMENT_TIMESTAMP_AND_EMOJI_BUTTONS,
                        "|CellType|ContainerType|ContainerType|ContainerType|ContainerType|ContainerType|"
                )
        );
    }
}
