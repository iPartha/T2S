package com.ipartha.t2s.mvvm


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = DBConstants.CONSUMER_MENU_TABLE_NAME)
data class ConsumerMenu(@PrimaryKey(autoGenerate = true)
                              @ColumnInfo(name = "row")
                              var row : Long = 0,
                              @ColumnInfo(name = DBConstants.CONSUMER_MENU_PRICE)
                              var price : Float = 0f,
                              @ColumnInfo(name = DBConstants.CONSUMER_MENU_NAME)
                              var name : String = "" )