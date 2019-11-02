package com.ipartha.t2s.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(databaseQuery: suspend () -> T,
                          networkCall: suspend () -> Result<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        val data = databaseQuery()
        if (data == null || (data is List<*> && data.size == 0)) {
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Result.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
                emit(Result.success(responseStatus.data as T))
            } else if (responseStatus.status == Result.Status.ERROR) {
                emit(Result.error<T>(responseStatus.message!!))
            }
        } else {
            emit(Result.success(data))
        }
    }

