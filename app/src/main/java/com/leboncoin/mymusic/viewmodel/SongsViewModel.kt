package com.leboncoin.mymusic.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leboncoin.mymusic.poko.Song
import com.leboncoin.mymusic.repository.SongsRepository
import com.leboncoin.mymusic.retrofit.ISongs
import kotlinx.coroutines.*

class SongsViewModel(application: Application) : AndroidViewModel(application) {

    private val songsRepository: ISongs
    private var job: Job? = null

    private val apiSongs = mutableListOf<Song>()

    private val mSongs = MutableLiveData(apiSongs)
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

                    apiSongs.apply {
                        clear()
                        addAll(response.body() ?: mutableListOf())
                    }

                    mSongs.postValue(apiSongs)
                } else {
                    // TODO: Load albums from DB
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
