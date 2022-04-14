package com.leboncoin.mymusic.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.leboncoin.mymusic.MyMusicApplication
import com.leboncoin.mymusic.helper.MapperHelper.mapSongsToAlbums
import com.leboncoin.mymusic.poko.Album
import com.leboncoin.mymusic.repository.AlbumsRepository
import com.leboncoin.mymusic.repository.SongsRepository
import com.leboncoin.mymusic.retrofit.ISongs
import kotlinx.coroutines.*

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {

    private val songsRepository: ISongs
    private val repository: AlbumsRepository

    private val apiAlbums = mutableListOf<Album>()

    private val mAlbums = MutableLiveData(apiAlbums)
    val albums: LiveData<MutableList<Album>>
        get() {
            return mAlbums
        }

    /**************
     *    INIT    *
     **************/
    init {
        songsRepository = SongsRepository.getInstance(application)
        repository = AlbumsRepository((application as MyMusicApplication).database.albumsDao())
    }

    /**************
     *    ROOM    *
     **************/
    fun getDBAlbums() {
        viewModelScope.launch {
            refreshList(repository.getAllAlbums())
        }
    }

    fun deleteAllAlbums() {
        viewModelScope.launch {
            repository.deleteAllAlbums()
        }
    }

    private suspend fun insertDBAlbums(albums: List<Album>) {
        viewModelScope.launch {
            repository.insertAlbums(albums)
        }
    }

    /*************
     *    GET    *
     *************/
    fun getAlbums() {
        viewModelScope.launch {
            try {
                val response = songsRepository.getAllSongs()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Default) {
                        val albums = response.body()?.mapSongsToAlbums()
                        refreshList(albums ?: mutableListOf())
                    }

                    insertDBAlbums(apiAlbums)
                } else {
                    Log.e("SongsViewModel", "Error: ${response.message()}")
                    getDBAlbums()
                }
            } catch (e: Exception) {
                Log.e("SongsViewModel", "Error catch: ${e.message}")
                getDBAlbums()
            }

        }
    }

    /****************
     *    CUSTOM    *
     ****************/
    private fun refreshList(albums: List<Album>) {
        apiAlbums.apply {
            clear()
            addAll(albums)
        }

        mAlbums.postValue(apiAlbums)
    }
}
