package com.ipartha.t2s.networking

import retrofit2.Response
import com.ipartha.t2s.data.Result

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        //ToDo handle failure
        //Timber.e(message)
        return Result.error("Network call has failed for a following reason: $message")
    }

}