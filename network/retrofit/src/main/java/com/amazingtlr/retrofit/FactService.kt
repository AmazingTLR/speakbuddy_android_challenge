package com.amazingtlr.retrofit

import com.amazingtlr.retrofit.model.RetrofitFactResponse
import retrofit2.Call
import retrofit2.http.GET

interface FactService {
    @GET("fact")
    fun getFact(): Call<RetrofitFactResponse>
}