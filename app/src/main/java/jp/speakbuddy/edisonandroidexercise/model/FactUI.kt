package jp.speakbuddy.edisonandroidexercise.model

data class FactUI(
    val id: String,
    val fact: String,
    val length: Int,
    val shouldDisplayLength: Boolean,
    val multipleCats: Boolean
)