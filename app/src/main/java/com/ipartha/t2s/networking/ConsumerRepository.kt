package com.ipartha.t2s.networking


//object ConsumerRepository {
//
//    private val mConsumerAPI : ConsumerAPI = RetrofitService.createService(ConsumerAPI::class.java)
//
//    fun getConsumerMenuOptions() {
//
//        mConsumerAPI.getConsumerMenuOptions().enqueue(object : Callback<List<ConsumerMenuModel>> {
//            override fun onResponse(call: Call<List<ConsumerMenuModel>>, response: Response<List<ConsumerMenuModel>>) {
//               for (model in response.body() as List) {
//                   Log.i("Partha ", model.name)
//               }
//            }
//
//            override fun onFailure(call: Call<List<ConsumerMenuModel>>, t: Throwable) {
//                //ToDo Handle the failure case
//                Log.i("Partha", call.toString())
//            }
//
//
//        })
//    }
//
//}