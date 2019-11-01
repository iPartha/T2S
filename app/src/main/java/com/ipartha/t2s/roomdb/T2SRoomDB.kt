package com.ipartha.t2s.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipartha.t2s.mvvm.ConsumerMenu

@Database(entities = [ConsumerMenu::class], version = 1, exportSchema = false)
abstract class T2SRoomDB : RoomDatabase() {
    abstract fun consumerMenuDao(): ConsumerMenuDao
}
