package com.kotlinpl.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import com.kotlinpl.core.presentation.designsystem.OTT_MultimoduleTheme

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    hasToolbar: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val configuration = LocalWindowInfo.current
    val density = LocalDensity.current

    val screenWithPx = with(density) {
        configuration.containerSize.width
    }

    // find smaller dimention accordint to screen orientation
    val smallDimension = minOf(
        configuration.containerSize.width,
        configuration.containerSize.height
    )

    val primaryColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        primaryColor,
                        MaterialTheme.colorScheme.background
                    ),
                    center = Offset(
                        x = screenWithPx / 2f,
                        y = -100f
                    ),
                    radius = smallDimension / 2f
                ),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(if(hasToolbar) {
                    Modifier
                } else {
                    Modifier.systemBarsPadding()
                })
        ) {
            content()
        }
    }
}


@Preview
@Composable
private fun GradientBackgroundPreview() {
    OTT_MultimoduleTheme {
        GradientBackground(
            modifier = Modifier.fillMaxSize()
        ) {  }
    }
}