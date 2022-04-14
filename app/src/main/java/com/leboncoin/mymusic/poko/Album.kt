package com.leboncoin.mymusic.poko

import androidx.room.*
import com.leboncoin.mymusic.db.converter.SongsConverter

@Entity(tableName = "table_albums")
data class Album(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val albumId: Long,
    @TypeConverters(SongsConverter::class) val songs: List<Song> = emptyList(),
)
