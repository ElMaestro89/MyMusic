package com.leboncoin.mymusic.helper

import com.leboncoin.mymusic.poko.Album
import com.leboncoin.mymusic.poko.Song

object MapperHelper {

    fun List<Song>.mapSongsToAlbums(): List<Album> {
        val mapSongs: MutableMap<Long, MutableList<Song>> = mutableMapOf()

        this.map {
            mapSongs[it.albumId] = mapSongs.build(it.albumId, it)
        }

        return mapSongs.map {
            Album(it.key, it.value)
        }
    }

    private fun MutableMap<Long, MutableList<Song>>.build(albumId: Long, song: Song): MutableList<Song> {
        this[albumId]?.add(song)

        return this[albumId] ?: mutableListOf()
    }
}
