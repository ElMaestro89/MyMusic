package com.leboncoin.mymusic.repository

import android.app.Application
import com.leboncoin.mymusic.helper.SingletonHolder
import com.leboncoin.mymusic.poko.Song
import com.leboncoin.mymusic.retrofit.RetrofitService
import com.leboncoin.mymusic.retrofit.ISongs
import retrofit2.Response

class SongsRepository private constructor(private val application: Application) : ISongs {

    companion object : SingletonHolder<SongsRepository, Application>(::SongsRepository)

    override suspend fun getAllSongs(): Response<List<Song>> =
        RetrofitService.getInstance(application.applicationContext).create(ISongs::class.java).getAllSongs()
}
