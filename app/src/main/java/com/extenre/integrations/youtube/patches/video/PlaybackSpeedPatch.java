/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.youtube.patches.video;

import static com.extenre.integrations.shared.utils.StringRef.str;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.apache.commons.lang3.BooleanUtils;

import com.extenre.integrations.shared.utils.Logger;
import com.extenre.integrations.shared.utils.Utils;
import com.extenre.integrations.youtube.patches.misc.requests.PlaylistRequest;
import com.extenre.integrations.youtube.patches.utils.PatchStatus;
import com.extenre.integrations.youtube.settings.Settings;
import com.extenre.integrations.youtube.shared.VideoInformation;
import com.extenre.integrations.youtube.whitelist.Whitelist;

@SuppressWarnings("unused")
public class PlaybackSpeedPatch {
    private static final long TOAST_DELAY_MILLISECONDS = 750;
    private static long lastTimeSpeedChanged;
    private static boolean isLiveStream;

    /**
     * Injection point.
     */
    public static void newVideoStarted(@NonNull String newlyLoadedChannelId, @NonNull String newlyLoadedChannelName,
                                       @NonNull String newlyLoadedVideoId, @NonNull String newlyLoadedVideoTitle,
                                       final long newlyLoadedVideoLength, boolean newlyLoadedLiveStreamValue) {
        isLiveStream = newlyLoadedLiveStreamValue;
        Logger.printDebug(() -> "newVideoStarted: " + newlyLoadedVideoId);

        final float defaultPlaybackSpeed = getDefaultPlaybackSpeed(newlyLoadedChannelId, newlyLoadedVideoId);
        Logger.printDebug(() -> "overridePlaybackSpeed: " + defaultPlaybackSpeed);

        VideoInformation.overridePlaybackSpeed(defaultPlaybackSpeed);
    }

    /**
     * Injection point.
     */
    public static void fetchPlaylistData(@NonNull String videoId, boolean isShortAndOpeningOrPlaying) {
        if (Settings.DISABLE_DEFAULT_PLAYBACK_SPEED_MUSIC.get()) {
            try {
                final boolean videoIdIsShort = VideoInformation.lastPlayerResponseIsShort();
                // Shorts shelf in home and subscription feed causes player response hook to be called,
                // and the 'is opening/playing' parameter will be false.
                // This hook will be called again when the Short is actually opened.
                if (videoIdIsShort && !isShortAndOpeningOrPlaying) {
                    return;
                }

                PlaylistRequest.fetchRequestIfNeeded(videoId);
            } catch (Exception ex) {
                Logger.printException(() -> "fetchPlaylistData failure", ex);
            }
        }
    }

    /**
     * Injection point.
     */
    public static float getPlaybackSpeedInShorts(final float playbackSpeed) {
        if (!VideoInformation.lastPlayerResponseIsShort())
            return playbackSpeed;
        if (!Settings.ENABLE_DEFAULT_PLAYBACK_SPEED_SHORTS.get())
            return playbackSpeed;

        float defaultPlaybackSpeed = getDefaultPlaybackSpeed(VideoInformation.getChannelId(), null);
        Logger.printDebug(() -> "overridePlaybackSpeed in Shorts: " + defaultPlaybackSpeed);

        return defaultPlaybackSpeed;
    }

    /**
     * Injection point.
     * Called when user selects a playback speed.
     *
     * @param playbackSpeed The playback speed the user selected
     */
    public static void userSelectedPlaybackSpeed(float playbackSpeed) {
        if (PatchStatus.RememberPlaybackSpeed() &&
                Settings.REMEMBER_PLAYBACK_SPEED_LAST_SELECTED.get()) {
            // With the 0.05x menu, if the speed is set by integrations to higher than 2.0x
            // then the menu will allow increasing without bounds but the max speed is
            // still capped to under 8.0x.
            playbackSpeed = Math.min(playbackSpeed, CustomPlaybackSpeedPatch.MAXIMUM_PLAYBACK_SPEED - 0.05f);

            // Prevent toast spamming if using the 0.05x adjustments.
            // Show exactly one toast after the user stops interacting with the speed menu.
            final long now = System.currentTimeMillis();
            lastTimeSpeedChanged = now;

            final float finalPlaybackSpeed = playbackSpeed;
            Utils.runOnMainThreadDelayed(() -> {
                if (lastTimeSpeedChanged != now) {
                    // The user made additional speed adjustments and this call is outdated.
                    return;
                }

                if (Settings.DEFAULT_PLAYBACK_SPEED.get() == finalPlaybackSpeed) {
                    // User changed to a different speed and immediately changed back.
                    // Or the user is going past 8.0x in the glitched out 0.05x menu.
                    return;
                }
                Settings.DEFAULT_PLAYBACK_SPEED.save(finalPlaybackSpeed);

                if (!Settings.REMEMBER_PLAYBACK_SPEED_LAST_SELECTED_TOAST.get()) {
                    return;
                }
                Utils.showToastShort(str("extenre_remember_playback_speed_toast", (finalPlaybackSpeed + "x")));
            }, TOAST_DELAY_MILLISECONDS);
        }
    }

    private static float getDefaultPlaybackSpeed(@NonNull String channelId, @Nullable String videoId) {
        return (Settings.DISABLE_DEFAULT_PLAYBACK_SPEED_LIVE.get() && isLiveStream) ||
                Whitelist.isChannelWhitelistedPlaybackSpeed(channelId) ||
                getPlaylistData(videoId)
                ? 1.0f
                : Settings.DEFAULT_PLAYBACK_SPEED.get();
    }

    private static boolean getPlaylistData(@Nullable String videoId) {
        if (Settings.DISABLE_DEFAULT_PLAYBACK_SPEED_MUSIC.get() && videoId != null) {
            try {
                PlaylistRequest request = PlaylistRequest.getRequestForVideoId(videoId);
                final boolean isPlaylist = request != null && BooleanUtils.toBoolean(request.getStream());
                Logger.printDebug(() -> "isPlaylist: " + isPlaylist);

                return isPlaylist;
            } catch (Exception ex) {
                Logger.printException(() -> "getPlaylistData failure", ex);
            }
        }

        return false;
    }
}
