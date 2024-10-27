package jp.speakbuddy.edisonandroidexercise.states

import jp.speakbuddy.edisonandroidexercise.model.FactUI

sealed interface FactListState {
    data class Success(val factList: List<FactUI>): FactListState
    data object Loading: FactListState
    data object Error: FactListState
}