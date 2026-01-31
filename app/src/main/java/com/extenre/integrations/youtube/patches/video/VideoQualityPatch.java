package com.extenre.integrations.youtube.patches.video;

import static com.extenre.integrations.shared.utils.StringRef.str;

import androidx.annotation.NonNull;

import com.extenre.integrations.shared.settings.IntegerSetting;
import com.extenre.integrations.shared.utils.Utils;
import com.extenre.integrations.youtube.settings.Settings;
import com.extenre.integrations.youtube.shared.PlayerType;
import com.extenre.integrations.youtube.shared.VideoInformation;

@SuppressWarnings("unused")
public class VideoQualityPatch {
    private static final int DEFAULT_YOUTUBE_VIDEO_QUALITY = -2;
    private static final IntegerSetting mobileQualitySetting = Settings.DEFAULT_VIDEO_QUALITY_MOBILE;
    private static final IntegerSetting wifiQualitySetting = Settings.DEFAULT_VIDEO_QUALITY_WIFI;

    @NonNull
    public static String videoId = "";

    /**
     * Injection point.
     */
    public static void newVideoStarted() {
        setVideoQuality(0);
    }

    /**
     * Injection point.
     */
    public static void newVideoStarted(@NonNull String newlyLoadedChannelId, @NonNull String newlyLoadedChannelName,
                                       @NonNull String newlyLoadedVideoId, @NonNull String newlyLoadedVideoTitle,
                                       final long newlyLoadedVideoLength, boolean newlyLoadedLiveStreamValue) {
        if (PlayerType.getCurrent() == PlayerType.INLINE_MINIMAL)
            return;
        if (videoId.equals(newlyLoadedVideoId))
            return;
        videoId = newlyLoadedVideoId;
        setVideoQuality(Settings.SKIP_PRELOADED_BUFFER.get() ? 250 : 500);
    }

    /**
     * Injection point.
     */
    public static void userSelectedVideoQuality() {
        Utils.runOnMainThreadDelayed(() ->
                        userSelectedVideoQuality(VideoInformation.getVideoQuality()),
                300
        );
    }

    private static void setVideoQuality(final long delayMillis) {
        final int defaultQuality = Utils.getNetworkType() == Utils.NetworkType.MOBILE
                ? mobileQualitySetting.get()
                : wifiQualitySetting.get();

        if (defaultQuality == DEFAULT_YOUTUBE_VIDEO_QUALITY)
            return;

        Utils.runOnMainThreadDelayed(() ->
                        VideoInformation.overrideVideoQuality(
                                VideoInformation.getAvailableVideoQuality(defaultQuality)
                        ),
                delayMillis
        );
    }

    private static void userSelectedVideoQuality(final int defaultQuality) {
        if (!Settings.REMEMBER_VIDEO_QUALITY_LAST_SELECTED.get())
            return;
        if (defaultQuality == DEFAULT_YOUTUBE_VIDEO_QUALITY)
            return;

        final Utils.NetworkType networkType = Utils.getNetworkType();

        switch (networkType) {
            case NONE -> {
                Utils.showToastShort(str("extenre_remember_video_quality_none"));
                return;
            }
            case MOBILE -> mobileQualitySetting.save(defaultQuality);
            default -> wifiQualitySetting.save(defaultQuality);
        }

        if (!Settings.REMEMBER_VIDEO_QUALITY_LAST_SELECTED_TOAST.get())
            return;

        Utils.showToastShort(str("extenre_remember_video_quality_" + networkType.getName(), defaultQuality + "p"));
    }
}