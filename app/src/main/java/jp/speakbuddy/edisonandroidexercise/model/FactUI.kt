package jp.speakbuddy.edisonandroidexercise.model

import com.amazingtlr.api.model.FactResponse

data class FactUI(
    val fact: String,
    val length: Int,
    val shouldDisplayLength: Boolean,
    val multipleCats: Boolean
)

fun FactResponse.toFactUI(): FactUI {
    return FactUI(
        fact = fact,
        length = fact.length,
        shouldDisplayLength = fact.length > 100,
        multipleCats = fact.contains("cats", ignoreCase = true)
    )
}