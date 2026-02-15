/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.misc;

import com.extenre.integrations.shared.utils.Logger;
import com.extenre.integrations.youtube.settings.Settings;

@SuppressWarnings("unused")
public class QUICProtocolPatch {

    public static boolean disableQUICProtocol(boolean original) {
        try {
            return !Settings.DISABLE_QUIC_PROTOCOL.get() && original;
        } catch (Exception ex) {
            Logger.printException(() -> "Failed to load disableQUICProtocol", ex);
        }
        return original;
    }
}
