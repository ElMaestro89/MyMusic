package com.leboncoin.mymusic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leboncoin.mymusic.poko.Song

class SongsViewModel(application: Application) : AndroidViewModel(application) {

    private val mockSongs by lazy {
        mutableListOf(
            Song(1L, "Song 1", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"),
            Song(2L, "Song 2", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796")
        )
    }

    private val mSongs = MutableLiveData(mockSongs)
    val songs: LiveData<MutableList<Song>>
        get() {
            return mSongs
        }

    fun testRefreshList() {
        mockSongs.apply {
            add(Song(2L, "Song 2", "https://via.placeholder.com/600/771796", "https://via.placeholder.com/150/771796"))
            add(Song(1L, "Song 1", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952"))
        }

        mSongs.postValue(mockSongs)
    }
}
