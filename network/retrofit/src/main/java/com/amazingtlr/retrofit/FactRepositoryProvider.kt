package com.amazingtlr.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object FactServiceProvider {

    private val json = Json {
        ignoreUnknownKeys = true // Ignore unknown fields
        isLenient = true // Allows non-standard JSON syntax
    }

    fun provide(): FactService =
        Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(FactService::class.java)
}