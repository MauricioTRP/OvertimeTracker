package com.kotlinpl.booking.presentation.activities

import com.kotlinpl.core.domain.booking.Activity

data class ActivitiesUiState(
    val isLoading: Boolean = false,
    val activities: List<Activity> = emptyList(),
    val error: String? = null
)
