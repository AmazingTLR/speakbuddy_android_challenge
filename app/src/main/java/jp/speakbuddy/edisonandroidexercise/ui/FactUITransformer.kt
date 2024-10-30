package jp.speakbuddy.edisonandroidexercise.ui

import com.amazingtlr.api.model.FactResponse
import jp.speakbuddy.edisonandroidexercise.model.FactUI

class FactUITransformer {
    operator fun invoke(factResponse: FactResponse): FactUI {
        return FactUI(
            id = factResponse.id,
            fact = factResponse.fact,
            length = factResponse.fact.length,
            shouldDisplayLength = factResponse.fact.length > 100,
            multipleCats = factResponse.fact.contains("cats", ignoreCase = true)
        )
    }
}