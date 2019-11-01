package com.ipartha.t2s.mvvm

import com.ipartha.t2s.roomdb.DBConstants


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = DBConstants.CONSUMER_MENU_TABLE_NAME)
data class ConsumerMenu(@PrimaryKey(autoGenerate = true)
                              @ColumnInfo(name = "row")
                              var row : Long = 0,
                              @ColumnInfo(name = DBConstants.CONSUMER_MENU_ID)
                              var id : Long = 0,
                              @ColumnInfo(name = DBConstants.CONSUMER_MENU_NAME)
                              var name : String = "" )