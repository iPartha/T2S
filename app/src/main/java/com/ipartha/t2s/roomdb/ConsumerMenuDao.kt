package com.ipartha.t2s.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ipartha.t2s.mvvm.ConsumerMenu

@Dao
interface ConsumerMenuDao {
    @Insert
    fun insertAll(menuOptionList : List<ConsumerMenu>)

    @Query("SELECT * FROM "+DBConstants.CONSUMER_MENU_TABLE_NAME)
    fun getAll() : LiveData<List<ConsumerMenu>>
}