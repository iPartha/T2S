package com.ipartha.t2s.mvvm

import com.ipartha.t2s.data.resultLiveData
import com.ipartha.t2s.networking.ConsumerRemoteSource
import com.ipartha.t2s.roomdb.ConsumerMenuDao

class ConsumerMenuRepository(private val dbDao: ConsumerMenuDao, private val remoteSource : ConsumerRemoteSource) {

    val consumerMenu = resultLiveData(
        databaseQuery = { dbDao.getAll() },
        networkCall = { remoteSource.fetchData() },
        saveCallResult = { dbDao.insertAll(it) })

}