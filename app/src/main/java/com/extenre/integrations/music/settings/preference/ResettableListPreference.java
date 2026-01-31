package com.extenre.integrations.music.settings.preference;

import static com.extenre.integrations.music.utils.ExtendedUtils.getDialogBuilder;
import static com.extenre.integrations.shared.utils.ResourceUtils.getStringArray;
import static com.extenre.integrations.shared.utils.StringRef.str;

import android.app.Activity;

import androidx.annotation.NonNull;

import java.util.Arrays;

import com.extenre.integrations.shared.settings.EnumSetting;
import com.extenre.integrations.shared.settings.Setting;
import com.extenre.integrations.shared.utils.Logger;

public class ResettableListPreference {
    private static int mClickedDialogEntryIndex;

    public static void showDialog(Activity mActivity, @NonNull Setting<String> setting, int defaultIndex) {
        try {
            final String settingsKey = setting.key;

            final String entryKey = settingsKey + "_entries";
            final String entryValueKey = settingsKey + "_entry_values";
            final String[] mEntries = getStringArray(entryKey);
            final String[] mEntryValues = getStringArray(entryValueKey);

            final int findIndex = Arrays.binarySearch(mEntryValues, setting.get());
            mClickedDialogEntryIndex = findIndex >= 0 ? findIndex : defaultIndex;

            getDialogBuilder(mActivity)
                    .setTitle(str(settingsKey + "_title"))
                    .setSingleChoiceItems(mEntries, mClickedDialogEntryIndex,
                            (dialog, id) -> mClickedDialogEntryIndex = id)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setNeutralButton(str("extenre_extended_settings_reset"), (dialog, which) -> {
                        setting.resetToDefault();
                        ExtenRePreferenceFragment.showRebootDialog();
                    })
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        setting.save(mEntryValues[mClickedDialogEntryIndex]);
                        ExtenRePreferenceFragment.showRebootDialog();
                    })
                    .show();
        } catch (Exception ex) {
            Logger.printException(() -> "showDialog failure", ex);
        }
    }

    public static void showDialog(Activity mActivity, @NonNull EnumSetting<?> setting, int defaultIndex) {
        try {
            final String settingsKey = setting.key;

            final String entryKey = settingsKey + "_entries";
            final String entryValueKey = settingsKey + "_entry_values";
            final String[] mEntries = getStringArray(entryKey);
            final String[] mEntryValues = getStringArray(entryValueKey);

            final int findIndex = Arrays.binarySearch(mEntryValues, setting.get().toString());
            mClickedDialogEntryIndex = findIndex >= 0 ? findIndex : defaultIndex;

            getDialogBuilder(mActivity)
                    .setTitle(str(settingsKey + "_title"))
                    .setSingleChoiceItems(mEntries, mClickedDialogEntryIndex,
                            (dialog, id) -> mClickedDialogEntryIndex = id)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setNeutralButton(str("extenre_extended_settings_reset"), (dialog, which) -> {
                        setting.resetToDefault();
                        ExtenRePreferenceFragment.showRebootDialog();
                    })
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        setting.saveValueFromString(mEntryValues[mClickedDialogEntryIndex]);
                        ExtenRePreferenceFragment.showRebootDialog();
                    })
                    .show();
        } catch (Exception ex) {
            Logger.printException(() -> "showDialog failure", ex);
        }
    }
}
