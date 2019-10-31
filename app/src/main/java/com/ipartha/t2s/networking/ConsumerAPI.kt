package com.ipartha.t2s.networking


import com.ipartha.t2s.roomdb.ConsumerMenuEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ConsumerAPI {
    //Todo : remove the hard coded header
    @Headers("Store: 288")
    @GET("/consumer/menu/options")
    suspend fun getConsumerMenuOptions() : Response<List<ConsumerMenuEntity>>
}