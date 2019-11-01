package com.ipartha.t2s.networking

import com.ipartha.t2s.data.Result


abstract class BaseDataSource {

    protected suspend fun <T> getResult(fetchData: suspend () -> T): Result<T> {
        return try {
            val response = fetchData()
            if (response == null) {
                error(" Empty response")
            } else {
                Result.success(response)
            }

        } catch (ex : Exception) {
            error(ex.message ?: ex.toString())
        }

    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("Network call has failed for a following reason: $message")
    }

}