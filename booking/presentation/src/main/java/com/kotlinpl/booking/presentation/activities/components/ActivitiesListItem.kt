package com.kotlinpl.booking.presentation.activities.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.presentation.designsystem.OTT_MultimoduleTheme
import com.kotlinpl.core.presentation.designsystem.R
import com.kotlinpl.core.presentation.designsystem.components.OttHorizontalCardWithPicture

@Composable
fun ActivitiesList(activities: List<Activity>, onActivityClick: (Activity) -> Unit = {}) {
    LazyColumn (
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(
            activities,
            // Used to keep track of possible recompositions
            key = { activity -> activity.id }
        ) { activity ->
            ActivityListItem(
                activity = activity,
                onClick = { onActivityClick(activity) }
            )
        }
    }
}

@Composable
private fun ActivityListItem(activity: Activity, onClick: () -> Unit = {}) {
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
private fun ActivityItemPreview() {
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