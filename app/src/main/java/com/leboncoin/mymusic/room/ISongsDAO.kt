package com.leboncoin.mymusic.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leboncoin.mymusic.poko.Song

@Dao
interface ISongsDAO {

    /****************
     *    INSERT    *
     ****************/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSongs(song: List<Song>)

    /***************
     *    QUERY    *
     ***************/
    @Query("SELECT * FROM table_songs ORDER BY id ASC")
    suspend fun getAllSongs(): List<Song>

    @Query("DELETE FROM table_songs")
    suspend fun deleteAll()
}
