package com.kotlinpl.booking.presentation.activities

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinpl.booking.presentation.activities.components.ActivitiesList
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.presentation.designsystem.OTT_MultimoduleTheme
import com.kotlinpl.core.presentation.designsystem.components.GradientBackground
import com.kotlinpl.core.presentation.designsystem.components.OttActionField

@Composable
fun ActivitiesScreenRoot(
    viewModel: ActivitiesViewModel,
    onBackClick: () -> Unit = {}, // TODO not yet implemented
    onNavigateToActivity: (Activity) -> Unit = {},
) {
    ActivitiesScreen(
        viewModel.state,
        onAction = { action ->
            when (action) {
                ActivitiesAction.OnSearchClick -> {
                    viewModel.onAction(ActivitiesAction.OnSearchClick)
                }
                is ActivitiesAction.OnActivityClick -> {
                    Log.d("ActivitiesScreenRoot", "ActivitiesScreenRoot: $action")
                    onNavigateToActivity(action.activity)
                }
            }
        },
        onNavigateToActivity = onNavigateToActivity
    )
}

/**
 * Activities Screen Composable
 *
 * @param state Accepts [ActivitiesUiState] to update UI accordingly
 * @param onAction Lambda to trigger Activities actions based on [ActivitiesAction]
 * @param onNavigateToActivity Lambda to navigate to Activity
 */
@Composable
fun ActivitiesScreen(
    state: ActivitiesUiState,
    onAction: (ActivitiesAction) -> Unit = {},
    onNavigateToActivity: (Activity) -> Unit = {}
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            /**
             * Search Bar
             */
            OttActionField(
                /* state bind to TextFieldState from UIState */
                state = state.searchQuery,
                endIcon = Icons.Default.Search,
                hint = "Search by place",
                title = "Search",
                keyboardType = KeyboardType.Text,
                enabled = state.canSearch,
                modifier = Modifier.padding(bottom = 16.dp),
                onDoneActionClick = {
                    onAction(ActivitiesAction.OnSearchClick)
                }
            )
            if(state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text("Error getting activities")
            } else {
                if(!state.activities.isEmpty()) {
                    ActivitiesList(
                        activities = state.activities,
                        onActivityClick = {
                            /**
                             * Update Activity
                             */
                            Log.d("ActivitiesScreen", "ActivitiesScreen: $it")
                            onAction(ActivitiesAction.OnActivityClick(it))
                        }
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun ActivitiesScreenPreview() {
    OTT_MultimoduleTheme {
        ActivitiesScreen(
            state = ActivitiesUiState()
        )
    }
}
