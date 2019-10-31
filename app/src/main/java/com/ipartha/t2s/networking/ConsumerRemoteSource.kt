package com.ipartha.t2s.networking

class ConsumerRemoteSource(private val consumerAPI: ConsumerAPI) : BaseDataSource() {

    suspend fun fetchData() = getResult { consumerAPI.getConsumerMenuOptions() }

}