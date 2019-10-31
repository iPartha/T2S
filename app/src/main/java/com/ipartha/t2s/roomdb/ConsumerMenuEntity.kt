package com.ipartha.t2s.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = DBConstants.CONSUMER_MENU.TABLE_NAME)
class ConsumerMenuEntity {

    @PrimaryKey
    @ColumnInfo(name = DBConstants.CONSUMER_MENU.ID)
    var id : Long = 0

    @ColumnInfo(name = DBConstants.CONSUMER_MENU.NAME)
    var name : String = ""

}