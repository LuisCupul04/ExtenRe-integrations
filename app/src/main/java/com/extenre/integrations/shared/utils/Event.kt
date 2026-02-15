/*
 * Copyright (C) 2026 LuisCupul04
 * Copyright (C) 2022 inotia00
 * Copyright (C) 2022 ReVanced LLC
 *
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.extenre.integrations.shared.utils

/**
 * generic event provider class
 */
class Event<T> {
    private val eventListeners = mutableSetOf<(T) -> Unit>()

    operator fun plusAssign(observer: (T) -> Unit) {
        addObserver(observer)
    }

    fun addObserver(observer: (T) -> Unit) {
        eventListeners.add(observer)
    }

    operator fun minusAssign(observer: (T) -> Unit) {
        removeObserver(observer)
    }

    private fun removeObserver(observer: (T) -> Unit) {
        eventListeners.remove(observer)
    }

    operator fun invoke(value: T) {
        for (observer in eventListeners)
            observer.invoke(value)
    }
}

