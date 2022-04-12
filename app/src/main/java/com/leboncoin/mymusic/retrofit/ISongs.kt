package com.leboncoin.mymusic.retrofit

import com.leboncoin.mymusic.poko.Song
import retrofit2.Response
import retrofit2.http.GET

interface ISongs {
    @GET("technical-test.json")
    suspend fun getAllSongs() : Response<List<Song>>
}
