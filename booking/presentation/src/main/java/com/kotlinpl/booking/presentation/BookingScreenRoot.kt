package com.kotlinpl.booking.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookingScreenRoot(
    onActivitySearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BookingScreen(
        modifier = modifier
    )
}

@Composable
private fun BookingScreen(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Booking Module",
        modifier = modifier
    )
}

@Preview (showBackground = true)
@Composable
private fun BookingScreenPreview() {
    BookingScreen(

    )
}