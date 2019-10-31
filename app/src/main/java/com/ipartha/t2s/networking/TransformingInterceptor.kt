package com.ipartha.t2s.networking

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Okio
import java.io.IOException
import java.io.InputStream
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener


abstract class TransformingInterceptor : Interceptor {


    @Throws(IOException::class)
    abstract fun transformInputStream(inputStream : String) : InputStream

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {

        val request = chain.request()
        val response = chain.proceed(request)
        val body = response.body()

        body?.let {
            try {
                val jsonObject = JSONTokener(body.string()).nextValue()

                if (jsonObject is JSONObject) {
                    val dataObject = jsonObject.get("data")
                    return response.newBuilder()
                        .body(ResponseBody.create(body.contentType(), body.contentLength(),
                        Okio.buffer(Okio.source(transformInputStream(dataObject.toString())))))
                        .build()
                } else {
                    throw Exception("Invalid response from "+chain.request().url().toString())
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        return null
    }
}
