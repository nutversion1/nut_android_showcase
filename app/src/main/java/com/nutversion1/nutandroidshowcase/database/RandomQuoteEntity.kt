package com.nutversion1.nutandroidshowcase.database

import androidx.room.*

@Entity(tableName = "randomQuotes")
data class RandomQuoteEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "name") val name: String,
)
