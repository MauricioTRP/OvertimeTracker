package com.kotlinpl.booking.presentation.single_activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.domain.booking.GeoCode
import com.kotlinpl.core.domain.booking.Price
import com.kotlinpl.core.presentation.designsystem.OTT_MultimoduleTheme

@Composable
fun SingleActivityScreenRoot(
    activityId: String,
    viewModel: SingleActivityViewModel,
    onToggleLike: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Scaffold { innerPadding ->
        LaunchedEffect(Unit) {
            viewModel.getActivityById(activityId)
        }

        SingleActivityScreen(
            state = viewModel.state,
            modifier = Modifier.padding(innerPadding),
            onAction = {}
        )
    }
}

@Composable
fun SingleActivityScreen(
    state: SingleActivityUiState,
    modifier: Modifier = Modifier,
    onAction: (SingleActivityAction) -> Unit = {},
) {
    Column(
        modifier = modifier
    ) {
        Text("Single Activity Screen")
        Text("Activity: ${state.activity?.name}")
        Text("Is liked: ${state.isLiked}")
        Text("Id: ${state.activity?.id}")
    }
}

@Preview
@Composable
private fun SingleActivityScreenPreview() {
    OTT_MultimoduleTheme {
        SingleActivityScreen(
            state = privateData()
        )
    }
}

private fun privateData() : SingleActivityUiState {
    return SingleActivityUiState(
        isLoading = false,
        activity = Activity(
            type = "tourism",
            id = "1",
            name = "Camping under water",
            description = "Breathing under water camping",
            geoCode = GeoCode(latitude = 0.0, longitude = 0.0),
            price = Price(currencyCode = "EUR", amount = 10.0),
            pictures = listOf("One Link"),
            bookingLink = "One Link",
            minimumDuration = "3 years"
        ),
        isLiked = false,
        error = null

    )
}
