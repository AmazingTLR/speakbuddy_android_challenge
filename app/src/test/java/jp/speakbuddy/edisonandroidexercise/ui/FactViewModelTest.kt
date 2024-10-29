package jp.speakbuddy.edisonandroidexercise.ui

import com.amazingtlr.usecase.fact.FactListUseCase
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactViewModel

class FactViewModelTest {
    private val facstListUseCase: FactListUseCase = mockk(relaxed = true)
    private val viewModel = FactViewModel(facstListUseCase)

    // Add tests here
}
