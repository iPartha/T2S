package com.ipartha.t2s.networking

import com.ipartha.t2s.mvvm.ConsumerMenuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ConsumerAPI {
    //Todo : remove the hard coded header
    @Headers("Store: 288")
    @GET("/consumer/menu/options")
    fun getConsumerMenuOptions() : Call<List<ConsumerMenuModel>>
}