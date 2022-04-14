package com.leboncoin.mymusic

import com.leboncoin.mymusic.helper.MapperHelper.mapSongsToAlbums
import com.leboncoin.mymusic.poko.Song
import org.junit.Assert.assertTrue
import org.junit.Test

class MapperUnitTest {
    @Test
    fun test_mapSongsToAlbums() {
        val songs = listOf(
            Song(1L, 1L, "Album 1 Song 1", "", ""),
            Song(1L, 2L, "Album 1 Song 2", "", ""),
            Song(2L, 3L, "Album 2 Song 3", "", ""),
            Song(2L, 4L, "Album 2 Song 4", "", ""),
            Song(2L, 5L, "Album 2 Song 5", "", ""),
            Song(3L, 6L, "Album 3 Song 6", "", "")
        )

        val result = songs.mapSongsToAlbums()

        assertTrue(result.isNotEmpty())
        assertTrue(result.size == 3)

        assertTrue(result[0].songs.size == 2)
        assertTrue(result[1].songs.size == 3)
        assertTrue(result[2].songs.size == 1)
    }
}
