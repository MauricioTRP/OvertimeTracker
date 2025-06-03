package com.kotlinpl.booking.presentation.activities

import com.kotlinpl.core.domain.booking.Activity

sealed interface ActivitiesAction {
    data object OnSearchClick: ActivitiesAction
    data class OnActivityClick(val activity: Activity) : ActivitiesAction
}
