package com.kotlinpl.booking.presentation.activities

import com.kotlinpl.core.presentation.ui.UiText

interface ActivitiesEvent {
    // Used for Activity Search
    data object ActivitySearch : ActivitiesEvent
    data object ActivityLikedToggle : ActivitiesEvent
    data class Error(val error: UiText) : ActivitiesEvent
}
