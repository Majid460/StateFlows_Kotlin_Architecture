package com.example.testapp.base

import com.example.testapp.utils.NetworkResult
import retrofit2.Response

abstract class BaseRemoteRepo {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>?): NetworkResult<T> {
        try {
            val response = apiCall.invoke()
            if (response != null && response.isSuccessful) {
                return NetworkResult.success(response.body())
            }
            val errorBody = response?.errorBody()?.string()
            return if (response?.code() == 404) {
                NetworkResult.error("Bad Request")
            } else if (!errorBody.isNullOrEmpty()) {
                NetworkResult.error(errorBody)
            } else {
                NetworkResult.error("Error in fetching data")
            }

        } catch (e: Exception) {
            println(e.message)
            throw RuntimeException(e)
        }
    }
}