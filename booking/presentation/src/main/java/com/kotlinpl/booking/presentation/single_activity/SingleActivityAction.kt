package com.kotlinpl.booking.presentation.single_activity

import com.kotlinpl.core.presentation.ui.UiText

interface SingleActivityAction {
    /*
     * Used as alert for "liked" or "unliked" activities
     */
    data object LikedActivityToggle : SingleActivityEvent
    data class Error(val error: UiText) : SingleActivityEvent
}