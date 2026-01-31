package com.extenre.integrations.youtube.patches.utils;

import com.extenre.integrations.youtube.shared.BottomSheetState;

@SuppressWarnings("unused")
public class BottomSheetHookPatch {
    /**
     * Injection point.
     */
    public static void onAttachedToWindow() {
        BottomSheetState.set(BottomSheetState.OPEN);
    }

    /**
     * Injection point.
     */
    public static void onDetachedFromWindow() {
        BottomSheetState.set(BottomSheetState.CLOSED);
    }
}

