package com.leboncoin.mymusic.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leboncoin.mymusic.R
import com.leboncoin.mymusic.poko.Song

@Database(entities = [Song::class], version = 1, exportSchema = false)
abstract class MyMusicDatabase: RoomDatabase() {

    abstract fun songsDao(): ISongsDAO

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
