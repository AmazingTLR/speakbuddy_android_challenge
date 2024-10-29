package jp.speakbuddy.edisonandroidexercise.ui.common.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage

@Composable
fun CircularImage(url: String, size: Dp, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = "Image of a cat",
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
}