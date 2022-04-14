package com.leboncoin.mymusic.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leboncoin.mymusic.poko.Album

@Dao
interface IAlbumDAO {

    /****************
     *    INSERT    *
     ****************/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlbums(albums: List<Album>)

    /***************
     *    QUERY    *
     ***************/
    @Query("SELECT * FROM table_albums ORDER BY id ASC")
    suspend fun getAllAlbums(): List<Album>

    @Query("DELETE FROM table_albums")
    suspend fun deleteAllAlbums()
}
