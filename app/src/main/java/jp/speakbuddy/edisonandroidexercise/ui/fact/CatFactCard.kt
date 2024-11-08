package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.ui.common.composable.CatImage
import jp.speakbuddy.edisonandroidexercise.ui.common.composable.DynamicTextField
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonTheme

private val catImageList = listOf(
    R.drawable.cat_1,
    R.drawable.cat_2,
    R.drawable.cat_3,
    R.drawable.cat_4,
    R.drawable.cat_5
)

@Composable
fun CatCardFact(
    factUI: FactUI,
    modifier: Modifier = Modifier
) {
    val randomCatImage by remember { mutableIntStateOf(catImageList.random()) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(EdisonTheme.dimensions.XL),
        shape = RoundedCornerShape(EdisonTheme.dimensions.M),
        colors = CardDefaults.cardColors().copy(
            containerColor = EdisonTheme.colors.background.default,
            contentColor = EdisonTheme.colors.text.default
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = EdisonTheme.dimensions.S),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(EdisonTheme.dimensions.M),
            modifier = modifier
                .fillMaxWidth()
                .padding(EdisonTheme.dimensions.L)
        ) {
            val fact = remember { factUI }

            CatImage(
                drawable = randomCatImage,
                size = 150.dp,
            )

            DynamicTextField(
                shouldDisplayText = fact.multipleCats,
                text = stringResource(R.string.multiple_cat_message),
                style = EdisonTheme.typography.normal,
            )

            Text(
                text = fact.fact,
                style = EdisonTheme.typography.largeAndBold,
            )

            DynamicTextField(
                shouldDisplayText = fact.shouldDisplayLength,
                text = stringResource(id = R.string.length_title, fact.length),
                style = EdisonTheme.typography.normal,
                modifier = modifier.align(Alignment.End)
            )
        }
    }
}

@Preview
@Composable
fun CatFactCardPreview() {
    CatCardFact(
        factUI = FactUI(
            id = "1",
            fact = "Cats are cute",
            length = 110,
            multipleCats = true,
            shouldDisplayLength = true
        ),
//        catImage = R.drawable.cat_1
    )
}