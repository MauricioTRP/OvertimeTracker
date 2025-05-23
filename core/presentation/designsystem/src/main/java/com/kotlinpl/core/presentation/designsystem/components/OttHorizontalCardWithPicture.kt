package com.kotlinpl.core.presentation.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kotlinpl.core.presentation.designsystem.R

@Composable
fun OttHorizontalCardWithPicture(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    title: String? = null,
    descriptionText: String? = null,
    imageVector: Painter? = null,
    cardDescription: String? = null,
    enabled: Boolean = true,
    onCardClick: () -> Unit
) {
    val cardDescription = cardDescription ?: stringResource(R.string.loading)
    val painter = imageVector ?: painterResource(id = R.drawable.ic_broken_image)
    val title = title ?: stringResource(R.string.loading)
    val descriptionText = descriptionText ?: stringResource(R.string.loading)

    Card(
        onClick = onCardClick,
        enabled = enabled,
        modifier = modifier
            .height(IntrinsicSize.Min)
            .semantics {
                contentDescription = cardDescription
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 125.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxHeight()
                        .heightIn(min = 50.dp, max = 150.dp)
                        .aspectRatio(1f)
                )
            } else {

                Image(
                    painter = painter,
                    contentDescription = descriptionText,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.small_padding)))
                        .aspectRatio(1f)
                            ,
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.medium_padding))
            ) {
                // Title
                Text(
                    text = if (isLoading) "" else title,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.small_padding))
                )
                Text(
                    text = descriptionText,
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(name = "OttCardPreview: No Image, loaded info")
@Composable
fun OttCardPreviewNoPicture() {
    OttHorizontalCardWithPicture(
        title = "Card Title",
        descriptionText = "Card description text that shows some information about the element you want to display",
        isLoading = false,
        cardDescription = "Card"
    ) { }
}

@Preview(name = "OttCardPreview: Loaded info + Image")
@Composable
fun OttCardPreviewWithPicture() {
    val pictureVector = painterResource(id = R.drawable.random_image)
    OttHorizontalCardWithPicture(
        title = "Card Title",
        descriptionText = "Card description text that shows some information about the element you want to display",
        isLoading = false,
        cardDescription = "Card",
        imageVector = pictureVector
    ) { }
}

@Preview(name = "OttCardPreview: Loagind")
@Composable
fun OttCardPreviewLoading() {
    val pictureVector = painterResource(id = R.drawable.random_image)
    OttHorizontalCardWithPicture(
        title = "",
        descriptionText = "",
        isLoading = true,
        cardDescription = "Card",
    ) { }
}