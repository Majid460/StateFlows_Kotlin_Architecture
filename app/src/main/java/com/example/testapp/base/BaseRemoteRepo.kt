package com.example.testapp.base

import com.example.testapp.utils.NetworkResult
import retrofit2.Response

abstract class BaseRemoteRepo {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>?): NetworkResult<T> {
        try{
            val response = apiCall.invoke()
            if(response != null && response.isSuccessful){
                return NetworkResult.success(response.body())
            }
            val errorBody = response?.errorBody()?.string()
            if (response?.code() == 404) {
                    return NetworkResult.error("Bad Request")
            } else if (!errorBody.isNullOrEmpty()) {
                    return NetworkResult.error(errorBody)
                }

        }catch (e: Exception) {
            println(e.message)
            throw RuntimeException(e)
        }
        return NetworkResult.loading()
    }
}