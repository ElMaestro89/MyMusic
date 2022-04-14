package com.leboncoin.mymusic.poko

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "table_songs",
    foreignKeys = [ForeignKey(
        entity = Album::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("album_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Song(
    @ColumnInfo(name = "album_id") val albumId: Long = 0L,
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String
)
