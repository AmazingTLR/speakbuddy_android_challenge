package jp.speakbuddy.edisonandroidexercise.ui

import com.amazingtlr.api.model.FactResponse
import org.junit.Test

class FactUITransformerTest {
    private val sut = FactUITransformer()

    @Test
    fun `When a factResponse is less than 100 characters & do not contains cats, shouldDisplayLength is false and multipleCats is false`(){
        val factResponse = FactResponse(
            id = "1",
            fact = "Cat is cute",
            length = 13
        )

        val result = sut(factResponse)

        assert(!result.shouldDisplayLength)
        assert(!result.multipleCats)
    }

    @Test
    fun `When a factResponse is less than 100 characters & contains cats, shouldDisplayLength is false and multipleCats is true`(){
        val factResponse = FactResponse(
            id = "1",
            fact = "Cats are cute",
            length = 13
        )

        val result = sut(factResponse)

        assert(!result.shouldDisplayLength)
        assert(result.multipleCats)
    }

    @Test
    fun `When a factResponse is more than 100 characters & do not contains cats, shouldDisplayLength is true and multipleCats is false`(){
        val factResponse = FactResponse(
            id = "1",
            fact = "Cat is cute, Cat is cute, Cat is cute, Cat is cute, Cat is cute, Cat is cute, Cat is cute, Cat is cute",
            length = 102
        )

        val result = sut(factResponse)

        assert(result.shouldDisplayLength)
        assert(!result.multipleCats)
    }


    @Test
    fun `When a factResponse is more than 100 characters & contains cats, shouldDisplayLength is true and multipleCats is true`(){
        val factResponse = FactResponse(
            id = "1",
            fact = "Cats are cute, Cats are cute, Cats are cute, Cats are cute, Cats are cute, Cats are cute, Cats are cute, Cats are cute",
            length = 118
        )

        val result = sut(factResponse)

        assert(result.shouldDisplayLength)
        assert(result.multipleCats)
    }
}