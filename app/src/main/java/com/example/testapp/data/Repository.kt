package com.example.testapp.data

import com.example.testapp.base.BaseRemoteRepo
import com.example.testapp.data.remoteData.ApiInterfaces
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class Repository @Inject constructor(private val api:ApiInterfaces,private val externalScope: CoroutineScope):BaseRemoteRepo() {

    fun getBooksData() = flow {
        emit(safeApiCall {
            api.getBooks()
        })
    }.flowOn(Dispatchers.IO).shareIn(
        scope = externalScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    fun getAuthorsData() = flow {
        emit(safeApiCall {
            api.getAllAuthors()
        })
    }.flowOn(Dispatchers.IO).shareIn(
        scope = externalScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )
}