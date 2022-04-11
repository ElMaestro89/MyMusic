package com.leboncoin.mymusic.poko

data class Album(val albumId: Long, val songs: List<Song> = emptyList())
