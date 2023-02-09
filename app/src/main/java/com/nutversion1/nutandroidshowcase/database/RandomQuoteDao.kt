package com.nutversion1.nutandroidshowcase.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RandomQuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: RandomQuoteEntity)

    @Query("DELETE FROM randomQuotes")
    fun deleteQuotes()

    @Query("SELECT * FROM randomQuotes")
    fun getQuotes(): List<RandomQuoteEntity>
}