package com.amazingtlr.retrofit

import com.amazingtlr.retrofit.model.RetrofitFactsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FactService {
    @GET("facts")
    fun getFacts(@Query("page") neededPage: Int): Call<RetrofitFactsResponse>
}