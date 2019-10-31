package com.ipartha.t2s.roomdb

import androidx.lifecycle.LiveData
import com.ipartha.t2s.mvvm.ConsumerMenuModel

interface ConsumerDBRepo {
    fun getAll() : LiveData<List<ConsumerMenuModel>>
    fun addAll(consumerMenuList : List<ConsumerMenuModel>)

}