package jp.speakbuddy.edisonandroidexercise.ui

import androidx.lifecycle.ViewModel
import com.amazingtlr.usecase.fact.FactUseCase
import com.amazingtlr.usecase.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FactViewModel@Inject constructor(private val factUseCase: FactUseCase): ViewModel() {
    fun updateFact(completion: () -> Unit): String =
        runBlocking {
            // Refactor this to use flow as soon as possible
            val useCaseResult = factUseCase.invoke().flowOn(Dispatchers.IO).firstOrNull()
            when(useCaseResult){
                is UseCaseResult.Success -> {
                    useCaseResult.value.fact
                }
                is UseCaseResult.Failure -> {
                    "Error"
                }
                null -> "Error"
            }.also {
                completion()
            }

        }
}
