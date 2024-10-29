package jp.speakbuddy.edisonandroidexercise.ui.fact

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazingtlr.api.model.FactListResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.fact.ClearFactListUseCase
import com.amazingtlr.usecase.fact.FactListUseCase
import com.amazingtlr.usecase.fact.MarkFactAsSeenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.model.toFactUI
import jp.speakbuddy.edisonandroidexercise.states.FactListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class FactViewModel @Inject constructor(
    private val factListUseCase: FactListUseCase,
    private val markFactAsSeenUseCase: MarkFactAsSeenUseCase,
    private val clearFactUseCase: ClearFactListUseCase
) : ViewModel() {
    private val lastFactPageStateFlow = MutableStateFlow(1)
    private val mutableFactListStateFlow: MutableStateFlow<List<FactUI>> =
        MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                clearFactUseCase.invoke()
            }
        }
    }

    val factListStateFlow: StateFlow<FactListState> by lazy {
        factListSharedFlow
            .map { factListResponse ->
                if (factListResponse.factList.isEmpty()) {
                    FactListState.Error
                } else {
                    val factList = mutableFactListStateFlow.updateAndGet {
                        (it + factListResponse.factList.map { it.toFactUI() }).distinctBy { it.id }
                    }


                    val hasMoreContent = factListResponse.currentPage < factListResponse.totalPages

                    FactListState.Success(factList, hasMoreContent)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = FactListState.Loading
            )
    }

    private val factListSharedFlow: SharedFlow<FactListResponse> by lazy {
        lastFactPageStateFlow.flatMapLatest { lastFactPage ->
            factListUseCase(lastFactPage)
                .flowOn(Dispatchers.IO)
                .map { useCaseResult ->
                    when (useCaseResult) {
                        is UseCaseResult.Failure -> {
                            Log.e(
                                TAG,
                                "Failed to fetch facts",
                                useCaseResult.cause
                            )
                            FactListResponse(
                                factList = emptyList(),
                                currentPage = 0,
                                totalPages = 0
                            )
                        }

                        is UseCaseResult.Success -> useCaseResult.value
                    }
                }
        }.shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            replay = 1
        )
    }

    fun markFactAsSeen(factId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                markFactAsSeenUseCase(factId)
            }
        }
    }

    fun loadMoreFacts() {
        lastFactPageStateFlow.update {
            it + 1
        }
    }

    companion object {
        private const val TAG = "FactViewModel"
    }

}
