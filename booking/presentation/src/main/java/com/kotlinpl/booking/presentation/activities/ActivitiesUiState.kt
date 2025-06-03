package com.kotlinpl.booking.presentation.activities

import androidx.compose.foundation.text.input.TextFieldState
import com.kotlinpl.core.domain.booking.Activity

data class ActivitiesUiState(
    val searchQuery: TextFieldState = TextFieldState(),
    val canSearch: Boolean = false,
    val isLoading: Boolean = false,
    val activities: List<Activity> = emptyList(),
    val selectedActivity: Activity? = null,
    val error: String? = null
)
