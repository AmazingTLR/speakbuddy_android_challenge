package jp.speakbuddy.edisonandroidexercise.ui.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import jp.speakbuddy.edisonandroidexercise.R

@Composable
fun CatImage(drawable: Int, size: Dp, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(
            model = drawable,
            contentScale = ContentScale.Fit
        ),
        contentDescription = stringResource(R.string.cat_image_content_description),
        modifier = modifier
            .size(size),
    )
}

@Composable
@Preview
fun CircularImagePreview() {
    CatImage(drawable = R.drawable.cat_1, size = 150.dp)
}