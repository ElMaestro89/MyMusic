package com.leboncoin.mymusic.retrofit

import android.content.Context
import com.leboncoin.mymusic.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

object CacheUtils {

    fun getOkHttpClient(context: Context): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(this)
            }

            builder.readTimeout(15, TimeUnit.SECONDS)
        }

        // Assigning a CacheDirectory
        val myCacheDir = File(context.cacheDir, "OkHttpCache")
        val cacheSize: Long = 1024 * 1024
        val cacheDir = Cache(myCacheDir, cacheSize)

        builder.cache(cacheDir)

        return builder.build()
    }
}
