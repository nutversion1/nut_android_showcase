package com.nutversion1.nutandroidshowcase.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RandomQuoteEntity::class],
    version = 2
)
abstract class RandomQuoteDatabase : RoomDatabase() {
    abstract fun randomQuoteDao(): RandomQuoteDao
}