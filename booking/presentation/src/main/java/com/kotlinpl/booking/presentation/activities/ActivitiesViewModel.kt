package com.kotlinpl.booking.presentation.activities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.booking.domain.activity.ActivityRepository
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor (
    private val activityRepository: ActivityRepository
) : ViewModel() {
    var activitiesUiState by mutableStateOf(ActivitiesUiState())
        private set

    fun getActivities() {
        viewModelScope.launch {
            activitiesUiState = activitiesUiState.copy(isLoading = true)

            val result = activityRepository.getActivities(
                latitude = 41.397158,
                longitude = 2.160873,
                radius = 4
            )

            when (result) {
                is Result.Error<DataError> -> {
                    activitiesUiState = activitiesUiState.copy(
                        isLoading = false,
                        error = result.error.toString()
                    )
                }
                is Result.Success<List<Activity>> -> {
                    activitiesUiState = activitiesUiState.copy(
                        isLoading = false,
                        activities = result.data
                    )
                }
            }
        }

    }
}