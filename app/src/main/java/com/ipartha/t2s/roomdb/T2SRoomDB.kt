package com.ipartha.t2s.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ConsumerMenuEntity::class], version = 1, exportSchema = false)
abstract class T2SRoomDB : RoomDatabase() {
    abstract fun consumerMenuDao(): ConsumerMenuDao
}
