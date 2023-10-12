package com.example.project6new

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class for storing and managing Note entities using Room.
 *
 * @property noteDao The Data Access Object (DAO) for performing database operations on Note entities.
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * Get an instance of the NoteDatabase or create one if it doesn't exist.
         *
         * @param context The application context.
         * @return The instance of the NoteDatabase.
         */
        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "notes_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}