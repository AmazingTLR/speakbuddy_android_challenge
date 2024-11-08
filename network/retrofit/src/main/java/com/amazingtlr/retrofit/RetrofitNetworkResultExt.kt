package com.amazingtlr.retrofit

import com.amazingtlr.api.NetworkException
import com.amazingtlr.api.NetworkResult
import retrofit2.Call
import retrofit2.HttpException

fun <T : Any> Call<T>.getAndParse(): NetworkResult<T> {
    return try {
        val response = this.execute()
        if (response.isSuccessful && response.body() != null) {
            NetworkResult.Success(response.body()!!)
        } else {
            NetworkResult.Error(NetworkException(HttpException(response).message()))
        }
    } catch (e: Exception) {
        NetworkResult.Error(e)
    }
}