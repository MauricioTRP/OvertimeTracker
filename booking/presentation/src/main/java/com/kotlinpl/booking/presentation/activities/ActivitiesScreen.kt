package com.kotlinpl.booking.presentation.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.presentation.designsystem.OTT_MultimoduleTheme
import com.kotlinpl.core.presentation.designsystem.R
import com.kotlinpl.core.presentation.designsystem.components.GradientBackground
import com.kotlinpl.core.presentation.designsystem.components.OttActionButton
import com.kotlinpl.core.presentation.designsystem.components.OttHorizontalCardWithPicture

@Composable
fun ActivitiesScreenRoot(
    viewModel: ActivitiesViewModel = hiltViewModel(),
    onClick: (ActivitiesScreenActions) -> Unit = {},
) {
    ActivitiesScreen(
        viewModel.activitiesUiState,
        onClick = { action ->
            when (action) {
                ActivitiesScreenActions.OnGetActivitiesClick -> viewModel.getActivities()
                ActivitiesScreenActions.OnActivityClick -> {  }
            }
        }
    )
}

@Composable
fun ActivitiesScreen(
    state: ActivitiesUiState,
    onClick: (ActivitiesScreenActions) -> Unit = {}
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            if(state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(text = state.error.toString())
                OttActionButton(
                    text = "Get Activities",
                    isLoading = false,
                    buttonDescription = "Click here to get activities",
                    modifier = Modifier.padding(vertical = 16.dp),
                    enabled = true,
                    onClick = { onClick(ActivitiesScreenActions.OnGetActivitiesClick) }
                )
            } else {
                Text(text = "Activities")
                OttActionButton(
                    text = "Get Activities",
                    isLoading = false,
                    buttonDescription = "Click here to get activities",
                    modifier = Modifier.padding(vertical = 16.dp),
                    enabled = true,
                    onClick = { onClick(ActivitiesScreenActions.OnGetActivitiesClick) }
                )
                if(!state.activities.isEmpty()) {
                    ActivitiesList(activities = state.activities)
                }
            }
        }
    }
}

@Composable
fun ActivitiesList(activities: List<Activity>, onClick: () -> Unit = {}) {
    LazyColumn{
        items(activities) { activity ->
            ActivityItem(
                activity = activity,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ActivityItem(activity: Activity, onClick: () -> Unit = {}) {
    val activityPicture = activity.pictures.firstOrNull() ?: ""

    val painter = rememberAsyncImagePainter(
        model = activityPicture,
        error = painterResource(R.drawable.random_image)
    )

    OttHorizontalCardWithPicture(
        isLoading = false,
        title = activity.name,
        descriptionText = activity.description,
        cardDescription = activity.description,
        imageVector = painter,
        enabled = true,
        onCardClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun ActivityItemPreview() {
    OTT_MultimoduleTheme {
        OttHorizontalCardWithPicture(
            isLoading = false,
            title = "Activity 1",
            descriptionText = "Description 1",
            cardDescription = "Activity 1",
            imageVector = painterResource(R.drawable.random_image),
            enabled = true,
            onCardClick = {}
        )
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
