package jp.speakbuddy.edisonandroidexercise.model

import com.amazingtlr.api.model.FactResponse

data class FactUI(
    val id: String,
    val fact: String,
    val length: Int,
    val shouldDisplayLength: Boolean,
    val multipleCats: Boolean
)

fun FactResponse.toFactUI(): FactUI {
    return FactUI(
        id = id,
        fact = fact,
        length = fact.length,
        shouldDisplayLength = fact.length > 100,
        multipleCats = fact.contains("cats", ignoreCase = true)
    )
}