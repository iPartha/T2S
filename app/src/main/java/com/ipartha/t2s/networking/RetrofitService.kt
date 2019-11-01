package com.ipartha.t2s.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor


class RetrofitService {

    companion object {

        var customGson : Gson = GsonBuilder().setLenient().create()

       var interceptor = HttpLoggingInterceptor()

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Base64DecodingInterceptor.base64DecodingInterceptor)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://qa-api.t2scdn.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .build()

        //ToDo inject the base URL
         fun <S> createService(serviceClass : Class<S>) : S {
            return retrofit.create(serviceClass)
        }
    }
}

