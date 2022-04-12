package com.leboncoin.mymusic.retrofit

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private var retrofit: Retrofit? = null

    fun getInstance(context: Context): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://static.leboncoin.fr/img/shared/")
                .client(CacheUtils.getOkHttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }
}
