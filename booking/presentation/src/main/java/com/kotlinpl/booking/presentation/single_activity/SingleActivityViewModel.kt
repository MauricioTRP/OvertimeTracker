package com.kotlinpl.booking.presentation.single_activity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.booking.domain.activity.ActivityRepository
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleActivityViewModel @Inject constructor(
    private val activityRepository: ActivityRepository
) : ViewModel() {
    var state by mutableStateOf(SingleActivityUiState())
        private set

    fun onAction(action: SingleActivityAction) {
        when (action) {
            SingleActivityAction.LikedActivityToggle -> {
                state = state.copy(
                    isLiked = !state.isLiked
                )
            }
        }
    }

    fun getActivityById(id: String) {
        viewModelScope.launch {
            Log.d("SingleActivityViewModel", "getActivityById: $id")
            state = state.copy(isLoading = true)

            val result = activityRepository.getActivityById(id)

            when (result) {
                is Result.Error<*> -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.error.toString()
                    )
                }
                is Result.Success<*> -> {
                    state = state.copy(
                        isLoading = false,
                        /* TODO cast to Activity and update state safely */
                        activity = result.data as Activity
                    )
                }
            }
        }
    }
}