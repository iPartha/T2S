package com.ipartha.t2s.mvvm

import android.content.Context
import androidx.room.Room
import com.ipartha.t2s.data.resultLiveData
import com.ipartha.t2s.networking.ConsumerAPI
import com.ipartha.t2s.networking.ConsumerRemoteSource
import com.ipartha.t2s.networking.RetrofitService
import com.ipartha.t2s.roomdb.DBConstants
import com.ipartha.t2s.roomdb.T2SRoomDB

class ConsumerMenuRepository(applicationContext : Context) {

    private val localDB =
        Room.databaseBuilder(applicationContext, T2SRoomDB::class.java, DBConstants.DB_NAME)
            .build()
    private val dbDao = localDB.consumerMenuDao()

    private val consumerAPI : ConsumerAPI = RetrofitService.createService(ConsumerAPI::class.java)
    private val remoteSource = ConsumerRemoteSource(consumerAPI)

    suspend fun getConsumerMenu() = resultLiveData(
        databaseQuery = { dbDao.getAll() },
        networkCall = { remoteSource.fetchData() },
        saveCallResult = { dbDao.insertAll(it) })

}