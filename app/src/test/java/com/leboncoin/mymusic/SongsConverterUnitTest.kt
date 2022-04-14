package com.leboncoin.mymusic

import com.leboncoin.mymusic.db.converter.SongsConverter
import com.leboncoin.mymusic.poko.Song
import org.junit.Assert.assertEquals
import org.junit.Test

class SongsConverterUnitTest {

    private val json = "[" +
            "{\"albumId\":1,\"id\":1,\"title\":\"Album 1 Song 1\",\"url\":\"\",\"thumbnailUrl\":\"\"}," +
            "{\"albumId\":2,\"id\":3,\"title\":\"Album 2 Song 3\",\"url\":\"\",\"thumbnailUrl\":\"\"}" +
            "]"

    private val songs = listOf(
        Song(1L, 1L, "Album 1 Song 1", "", ""),
        Song(2L, 3L, "Album 2 Song 3", "", "")
    )

    @Test
    fun test_fromSongList() {
        val result = SongsConverter().fromSongList(songs)

        assertEquals(json, result)
    }

    @Test
    fun test_toSongList() {
        val result = SongsConverter().toSongList(json)

        assertEquals(songs, result)
    }
}
