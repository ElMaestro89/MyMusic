package com.leboncoin.mymusic.helper

import com.leboncoin.mymusic.poko.Album
import com.leboncoin.mymusic.poko.Song

object MapperHelper {

    fun List<Song>.mapSongsToAlbums(): List<Album> {
        val mapSongs: MutableMap<Long, MutableList<Song>> = mutableMapOf()

        this.map {
            if (mapSongs.containsKey(it.albumId))
                mapSongs[it.albumId] = mapSongs.build(it.albumId, it)
            else
                mapSongs.put(it.albumId, mutableListOf(it))
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
