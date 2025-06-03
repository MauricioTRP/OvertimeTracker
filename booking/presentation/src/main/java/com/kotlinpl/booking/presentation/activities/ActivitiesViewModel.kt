package com.kotlinpl.booking.presentation.activities

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.booking.domain.activity.ActivityRepository
import com.kotlinpl.booking.domain.activity.OttGeocoderAdapter
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.domain.booking.isNotEmpty
import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
import com.kotlinpl.core.presentation.ui.textAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ActivitiesViewModel"

@HiltViewModel
class ActivitiesViewModel @Inject constructor (
    private val activityRepository: ActivityRepository,
    private val geocoder: OttGeocoderAdapter,
) : ViewModel() {
    var state by mutableStateOf(ActivitiesUiState())
        private set

    private val eventChannel = Channel<ActivitiesEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.searchQuery.textAsFlow()
            .onEach { query ->
                /**
                 * Update "CanSearch" state using searchQuery<TextFieldState>
                 */
                val canSearch = query.isNotEmpty()
                state = state.copy(
                    canSearch = canSearch
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: ActivitiesAction) {
        when (action) {
            is ActivitiesAction.OnActivityClick -> {
                state = state.copy(
                    selectedActivity = action.activity
                )
            }
            ActivitiesAction.OnSearchClick -> {
                searchActivities(state.searchQuery.text.toString())
            }
        }
    }

    private fun searchActivities(searchQuery: String) {
        viewModelScope.launch {
            /**
             * Need to transform from city to coordinates
             */
            val addresses = geocoder.getCoordinatesFromAddress(searchQuery)

            Log.d(TAG, "searchActivities: $addresses")
            if (addresses.isNotEmpty() == true) {
                getActivities(
                    latitude = addresses.latitude!!,
                    longitude = addresses.longitude!!
                )
            }
        }
    }

    private fun getActivities(latitude: Double, longitude: Double, radius: Int = 4) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val result = activityRepository.getActivities(
                latitude = latitude,
                longitude = longitude,
                radius = radius
            )

            when (result) {
                is Result.Error<DataError> -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.error.toString()
                    )
                }
                is Result.Success<List<Activity>> -> {
                    state = state.copy(
                        isLoading = false,
                        activities = result.data
                    )
                }
            }
        }
    }
}