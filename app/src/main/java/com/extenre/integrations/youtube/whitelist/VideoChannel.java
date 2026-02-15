/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.whitelist;

import java.io.Serializable;

public final class VideoChannel implements Serializable {
    private final String channelName;
    private final String channelId;

    public VideoChannel(String channelName, String channelId) {
        this.channelName = channelName;
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelId() {
        return channelId;
    }
}
