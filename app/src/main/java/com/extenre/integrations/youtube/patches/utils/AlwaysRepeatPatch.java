package com.extenre.integrations.youtube.patches.utils;

import static com.extenre.integrations.youtube.utils.VideoUtils.pauseMedia;

import com.extenre.integrations.shared.utils.Utils;
import com.extenre.integrations.youtube.settings.Settings;
import com.extenre.integrations.youtube.shared.VideoInformation;

@SuppressWarnings("unused")
public class AlwaysRepeatPatch extends Utils {

    /**
     * Injection point.
     *
     * @return video is repeated.
     */
    public static boolean alwaysRepeat() {
        return alwaysRepeatEnabled() && VideoInformation.overrideVideoTime(0);
    }

    public static boolean alwaysRepeatEnabled() {
        final boolean alwaysRepeat = Settings.ALWAYS_REPEAT.get();
        final boolean alwaysRepeatPause = Settings.ALWAYS_REPEAT_PAUSE.get();

        if (alwaysRepeat && alwaysRepeatPause) pauseMedia();
        return alwaysRepeat;
    }

}
