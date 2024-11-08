package jp.speakbuddy.edisonandroidexercise.states

import jp.speakbuddy.edisonandroidexercise.model.FactUI

sealed interface HistoryFactListState {
    data class Success(val factList: List<FactUI>): HistoryFactListState
    data object Loading: HistoryFactListState
    data object Error: HistoryFactListState
}