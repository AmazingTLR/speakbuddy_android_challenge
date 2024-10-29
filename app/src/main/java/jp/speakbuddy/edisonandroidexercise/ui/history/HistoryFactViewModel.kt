package jp.speakbuddy.edisonandroidexercise.ui.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.fact.HistoryFactListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.model.toFactUI
import jp.speakbuddy.edisonandroidexercise.states.HistoryFactListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.updateAndGet
import javax.inject.Inject

@HiltViewModel
class HistoryFactViewModel @Inject constructor(
    private val historyFactListUseCase: HistoryFactListUseCase
) : ViewModel() {
    private val mutableFactListStateFlow: MutableStateFlow<List<FactUI>> =
        MutableStateFlow(emptyList())

    val historyFactListStateFlow: StateFlow<HistoryFactListState> by lazy {
        historyFactListSharedFlow
            .map { historyFactList ->
                if (historyFactList.isEmpty()) {
                    HistoryFactListState.Error
                } else {
                    val factList = mutableFactListStateFlow.updateAndGet {
                        (it + historyFactList.map { it.toFactUI() }).distinctBy { it.id }
                    }

                    HistoryFactListState.Success(factList)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = HistoryFactListState.Loading
            )
    }

    private val historyFactListSharedFlow: SharedFlow<List<FactResponse>> by lazy {
        historyFactListUseCase()
            .flowOn(Dispatchers.IO)
            .map { useCaseResult ->
                when (useCaseResult) {
                    is UseCaseResult.Failure -> {
                        Log.e(
                            TAG,
                            "Failed to fetch facts",
                            useCaseResult.cause
                        )
                        emptyList()
                    }

                    is UseCaseResult.Success -> useCaseResult.value
                }
            }.shareIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                replay = 1
            )
    }

    companion object {
        const val TAG = "HistoryFactViewModel"
    }
}
