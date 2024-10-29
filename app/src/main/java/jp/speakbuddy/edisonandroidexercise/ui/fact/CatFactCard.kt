package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.ui.common.composable.CircularImage
import jp.speakbuddy.edisonandroidexercise.ui.common.composable.DynamicTextField

@Composable
fun CatCardFact(
    factUI: FactUI,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black,
            disabledContainerColor = Color.Cyan,
            disabledContentColor = Color.Blue,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val fact = remember { factUI }

            CircularImage(
                url = "https://icons.iconarchive.com/icons/iconarchive/cute-animal/512/Cute-Cat-icon.png",
                size = 100.dp,
            )

            DynamicTextField(
                shouldDisplayText = fact.multipleCats,
                text = stringResource(R.string.multiple_cat_message),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            )

            Text(
                text = fact.fact,
                style = MaterialTheme.typography.bodyMedium
            )

            DynamicTextField(
                shouldDisplayText = fact.shouldDisplayLength,
                text = stringResource(id = R.string.length_title, fact.length),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
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
            fact = "Cats are cute",
            length = 110,
            multipleCats = true,
            shouldDisplayLength = true
        )
    )
}