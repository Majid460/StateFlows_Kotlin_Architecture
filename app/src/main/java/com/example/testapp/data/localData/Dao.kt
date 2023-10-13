package com.example.testapp.data.localData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.models.books.BooksResponse
import com.example.testapp.utils.NetworkResult
import com.pubnub.api.models.consumer.access_manager.v3.User
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(  books: MutableList<BooksResponse>)

    @Query("SELECT * FROM books")
    fun getBooks(): Flow<MutableList<BooksResponse>>
    @Query("DELETE FROM books")
    fun delete()
}