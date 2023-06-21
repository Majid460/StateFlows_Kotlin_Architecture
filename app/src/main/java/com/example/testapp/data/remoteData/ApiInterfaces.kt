package com.example.testapp.data.remoteData

import com.example.testapp.data.models.authors.AuthorsResponseModel
import com.example.testapp.data.models.books.BooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterfaces {
    @GET("getAllBooks")
    suspend fun getBooks():Response<MutableList<BooksResponse>>

    @GET("getAllAuthors")
    suspend fun getAllAuthors():Response<MutableList<AuthorsResponseModel>>
}