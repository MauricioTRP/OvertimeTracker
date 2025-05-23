package com.kotlinpl.booking.presentation.activities

sealed interface ActivitiesScreenActions {
    data object OnGetActivitiesClick: ActivitiesScreenActions
    data object OnActivityClick : ActivitiesScreenActions
}