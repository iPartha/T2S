package com.ipartha.t2s.networking

import android.util.Base64
import android.util.Log
import okhttp3.Interceptor
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.util.zip.Inflater
import java.util.zip.InflaterOutputStream

class Base64DecodingInterceptor private constructor() : TransformingInterceptor() {

    private val TAG = Base64DecodingInterceptor::class.java.name

   override fun transformInputStream(inputStream: String): InputStream {
       val decodedBytes = decodeBase64(inputStream)
       val stream = ByteArrayOutputStream()
       val decomposer = Inflater(false)
       val inflaterOutputStream = InflaterOutputStream(stream, decomposer)
       try {
           inflaterOutputStream.write(decodedBytes)
           inflaterOutputStream.close()
       } catch (ex : IOException) {

       }
       return stream.toByteArray().inputStream()
    }

    private fun decodeBase64(encodedResponse : String) : ByteArray {
        var decodedResponse = ByteArray(0)
        try {
            decodedResponse = Base64.decode(encodedResponse.toByteArray(charset("UTF-8")), Base64.DEFAULT)
        }catch (ex : UnsupportedEncodingException) {
            Log.e(TAG, ex.message)
        }

        return decodedResponse
    }

    companion object {
        val base64DecodingInterceptor: Interceptor = Base64DecodingInterceptor()
    }

}