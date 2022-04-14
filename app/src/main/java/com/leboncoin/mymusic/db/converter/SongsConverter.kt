package com.leboncoin.mymusic.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leboncoin.mymusic.poko.Song

class SongsConverter {

    @TypeConverter
    fun fromSongList(value: List<Song>): String {
        val type = object : TypeToken<List<Song>>() {}.type

        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Song> {
        val type = object : TypeToken<List<Song>>() {}.type

        return Gson().fromJson(value, type)
    }
}
