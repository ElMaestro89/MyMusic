package com.leboncoin.mymusic.retrofit

import android.content.Context
import com.leboncoin.mymusic.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun getRetrofitClient(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(CacheUtils.getOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
