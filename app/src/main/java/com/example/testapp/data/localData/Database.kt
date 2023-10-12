package com.example.testapp.data.localData

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.data.models.books.BooksResponse

@Database(
    entities = [BooksResponse::class],
    version=1,
    exportSchema = false
)
abstract class Database:RoomDatabase() {
    abstract fun getDao() : Dao
}