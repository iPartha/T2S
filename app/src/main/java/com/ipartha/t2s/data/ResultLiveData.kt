package com.ipartha.t2s.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

suspend fun <T, A> resultLiveData(databaseQuery: suspend () -> T,
                          networkCall: suspend () -> Result<A>,
                          saveCallResult: suspend (A) -> Unit): Result<T> {
        val data = databaseQuery()
        if (data == null || (data is List<*> && data.size == 0)) {
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Result.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
                return (Result.success(responseStatus.data as T))
            } else if (responseStatus.status == Result.Status.ERROR) {
                return (Result.error(responseStatus.message!!))
            }
        }

        return (Result.success(data))
    }

