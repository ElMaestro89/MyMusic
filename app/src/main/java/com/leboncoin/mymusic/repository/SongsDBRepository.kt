package com.leboncoin.mymusic.repository

import androidx.annotation.WorkerThread
import com.leboncoin.mymusic.poko.Song
import com.leboncoin.mymusic.room.ISongsDAO

class SongsDBRepository(private val songsDao: ISongsDAO) {

    @WorkerThread
    suspend fun getAllSongs(): List<Song> {
        return songsDao.getAllSongs()
    }

    @WorkerThread
    suspend fun insertSongs(songs: List<Song>) {
        songsDao.insertSongs(songs)
    }

    @WorkerThread
    suspend fun deleteAllSongs() {
        songsDao.deleteAll()
    }
}
