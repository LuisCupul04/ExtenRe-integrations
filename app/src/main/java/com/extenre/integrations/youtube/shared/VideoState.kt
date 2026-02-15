/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.shared

import com.extenre.integrations.shared.utils.Logger

/**
 * VideoState playback state.
 */
enum class VideoState {
    NEW,
    PLAYING,
    PAUSED,
    RECOVERABLE_ERROR,
    UNRECOVERABLE_ERROR,
    ENDED;

    companion object {

        private val nameToVideoState = entries.associateBy { it.name }

        @JvmStatic
        fun setFromString(enumName: String) {
            val state = nameToVideoState[enumName]
            if (state == null) {
                Logger.printException { "Unknown VideoState encountered: $enumName" }
            } else if (current != state) {
                Logger.printDebug { "VideoState changed to: $state" }
                current = state
            }
        }

        /**
         * Depending on which hook this is called from,
         * this value may not be up to date with the actual playback state.
         */
        @JvmStatic
        var current
            get() = currentVideoState
            private set(value) {
                currentVideoState = value
            }

        private var currentVideoState: VideoState? = null
    }
}