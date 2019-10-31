package com.ipartha.t2s.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = DBConstants.CONSUMER_MENU.TABLE_NAME)
class ConsumerMenuEntity {

    @PrimaryKey
    @ColumnInfo(name = DBConstants.CONSUMER_MENU.ID)
    private var id : Int = 0

    @ColumnInfo(name = DBConstants.CONSUMER_MENU.NAME)
    private var name : String = ""

}