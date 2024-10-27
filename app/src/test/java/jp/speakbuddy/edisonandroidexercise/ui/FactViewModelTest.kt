package jp.speakbuddy.edisonandroidexercise.ui

import com.amazingtlr.usecase.fact.FactUseCase
import io.mockk.mockk
import org.junit.Test

class FactViewModelTest {
    private val factUseCase: FactUseCase = mockk(relaxed = true)
    private val viewModel = FactViewModel(factUseCase)

    @Test
    fun updateFact() {
        var loading = true
        val initialFact = "initial"
        var fact = initialFact

        fact = viewModel.updateFact { loading = false }

        assert(!loading)
        assert(fact != initialFact)
    }
}
