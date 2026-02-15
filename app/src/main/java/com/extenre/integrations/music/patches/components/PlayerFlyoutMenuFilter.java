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
public final class PlayerFlyoutMenuFilter extends Filter {

    public PlayerFlyoutMenuFilter() {
        addIdentifierCallbacks(
                new StringFilterGroup(
                        Settings.HIDE_FLYOUT_MENU_3_COLUMN_COMPONENT,
                        "music_highlight_menu_item_carousel.eml",
                        "tile_button_carousel.eml"
                )
        );
    }
}
