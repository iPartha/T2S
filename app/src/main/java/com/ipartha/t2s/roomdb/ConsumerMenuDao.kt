package com.ipartha.t2s.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ipartha.t2s.mvvm.ConsumerMenuModel

@Dao
interface ConsumerMenuDao {
    @Insert
    fun insertAll(menuOptionList : List<ConsumerMenuEntity>)

    @Query("SELECT * FROM "+DBConstants.CONSUMER_MENU.TABLE_NAME)
    fun getAll() : LiveData<List<ConsumerMenuEntity>>
}