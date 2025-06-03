package com.kotlinpl.booking.presentation.single_activity

import com.kotlinpl.core.domain.booking.Activity

data class SingleActivityUiState(
    val isLoading: Boolean = false,
    val activity: Activity? = null,
    val isLiked: Boolean = false,
    val error: String? = null
)
