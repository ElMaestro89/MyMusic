package com.leboncoin.mymusic.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leboncoin.mymusic.helper.MapperHelper.mapSongsToAlbums
import com.leboncoin.mymusic.poko.Song
import com.leboncoin.mymusic.repository.SongsRepository
import com.leboncoin.mymusic.retrofit.ISongs
import kotlinx.coroutines.*

class SongsViewModel(application: Application) : AndroidViewModel(application) {

    private val songsRepository: ISongs
    private var job: Job? = null

    private val mockSongs by lazy {
        mutableListOf(
            Song(1L, 1L, "Song 1", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"),
            Song(1L, 2L, "Song 2", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796")
        )
    }

    private val mSongs = MutableLiveData(mockSongs)
    val songs: LiveData<MutableList<Song>>
        get() {
            return mSongs
        }

    /**************
     *    INIT    *
     **************/
    init {
        songsRepository = SongsRepository.getInstance(application)
    }

    /*************
     *    GET    *
     *************/
    fun getSongs() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = songsRepository.getAllSongs()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
//                    withContext(Dispatchers.Default) {
//                        val albums = response.body()?.mapSongsToAlbums()
//                    }

                    mockSongs.apply {
                        clear()
                        addAll(response.body() ?: mutableListOf())
                    }

                    mSongs.postValue(mockSongs)
                } else {
                    Log.e("SongsViewModel", "Error: ${response.message()}")
                }
            }
        }
    }

    /*******************
     *    LIFECYCLE    *
     *******************/
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
