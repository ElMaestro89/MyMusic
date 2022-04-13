package com.leboncoin.mymusic

import android.app.Application
import com.leboncoin.mymusic.retrofit.RetrofitService
import retrofit2.Retrofit

class MyMusicApplication : Application() {
    val retrofitClient: Retrofit by lazy {
        RetrofitService.getRetrofitClient(this)
    }
}
