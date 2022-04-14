package com.leboncoin.mymusic.repository

import androidx.annotation.WorkerThread
import com.leboncoin.mymusic.poko.Album
import com.leboncoin.mymusic.db.dao.IAlbumDAO

class AlbumsRepository(private val albumDao: IAlbumDAO) {

    @WorkerThread
    suspend fun getAllAlbums(): List<Album> {
        return albumDao.getAllAlbums()
    }

    @WorkerThread
    suspend fun insertAlbums(albums: List<Album>) {
        albumDao.insertAlbums(albums)
    }

    @WorkerThread
    suspend fun deleteAllAlbums() {
        albumDao.deleteAllAlbums()
    }
}
