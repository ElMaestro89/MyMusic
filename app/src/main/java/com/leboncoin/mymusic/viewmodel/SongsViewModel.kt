package com.leboncoin.mymusic.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.leboncoin.mymusic.MyMusicApplication
import com.leboncoin.mymusic.poko.Song
import com.leboncoin.mymusic.repository.SongsDBRepository
import com.leboncoin.mymusic.repository.SongsRepository
import com.leboncoin.mymusic.retrofit.ISongs
import kotlinx.coroutines.*

class SongsViewModel(application: Application) : AndroidViewModel(application) {

    private val songsRepository: ISongs
    private val repository: SongsDBRepository

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
        repository = SongsDBRepository((application as MyMusicApplication).database.songsDao())
    }

    /**************
     *    ROOM    *
     **************/
    fun getDBSongs() {
        viewModelScope.launch {
            refreshList(repository.getAllSongs())
        }
    }

    fun deleteAllSongs() {
        viewModelScope.launch {
            repository.deleteAllSongs()
        }
    }

    private suspend fun insertDBSongs(songs: List<Song>) = viewModelScope.launch {
        repository.insertSongs(songs)
    }

    /*************
     *    GET    *
     *************/
    fun getSongs() {
        viewModelScope.launch {
            val response = songsRepository.getAllSongs()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
//                    withContext(Dispatchers.Default) {
//                        val albums = response.body()?.mapSongsToAlbums()
//                    }

                    refreshList(response.body() ?: mutableListOf())

                    insertDBSongs(apiSongs)
                } else {
                    Log.e("SongsViewModel", "Error: ${response.message()}")

                    getDBSongs()
                }
            }
        }
    }

    /****************
     *    CUSTOM    *
     ****************/
    private fun refreshList(songs: List<Song>) {
        apiSongs.apply {
            clear()
            addAll(songs)
        }

        mSongs.postValue(apiSongs)
    }
}
