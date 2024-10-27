package jp.speakbuddy.edisonandroidexercise.ui

import com.amazingtlr.usecase.fact.FactListUseCase
import io.mockk.mockk

class FactViewModelTest {
    private val facstListUseCase: FactListUseCase = mockk(relaxed = true)
    private val viewModel = FactViewModel(facstListUseCase)

    // Add tests here
}
