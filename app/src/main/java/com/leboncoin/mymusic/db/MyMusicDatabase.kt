package com.leboncoin.mymusic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leboncoin.mymusic.R
import com.leboncoin.mymusic.poko.Album
import com.leboncoin.mymusic.poko.Song
import com.leboncoin.mymusic.db.converter.SongsConverter
import com.leboncoin.mymusic.db.dao.IAlbumDAO

@Database(entities = [Album::class, Song::class], version = 1, exportSchema = false)
@TypeConverters(SongsConverter::class)
abstract class MyMusicDatabase: RoomDatabase() {

    abstract fun albumsDao(): IAlbumDAO

    companion object {
        @Volatile
        private var instance: MyMusicDatabase? = null

        fun getDatabase(context: Context): MyMusicDatabase {
            return instance ?: synchronized(this) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyMusicDatabase::class.java,
                    context.applicationContext.getString(R.string.database_name)
                ).build()

                instance!!
            }
        }
    }
}
