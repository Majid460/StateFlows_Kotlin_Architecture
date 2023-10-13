package com.example.testapp.data

import com.example.testapp.base.BaseRemoteRepo
import com.example.testapp.data.localData.Database
import com.example.testapp.data.models.books.BooksResponse
import com.example.testapp.data.remoteData.ApiInterfaces
import com.example.testapp.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

import javax.inject.Inject


class Repository @Inject constructor(
    private val api: ApiInterfaces,
    private val externalScope: CoroutineScope,
    private val localData: Database,
) : BaseRemoteRepo() {

   fun getBooksLocally(): Flow<NetworkResult<MutableList<BooksResponse>>> {
        return localData.getDao().getBooks()
            .map { data ->
                NetworkResult.success(data)
            }
            .onStart {
                emit(NetworkResult.loading())
            }
            .catch { exception ->
                emit(NetworkResult.error(exception.message ?: "Error Occur"))
            }
    }

    suspend fun getBooksData() {
        externalScope.launch(Dispatchers.IO) {
            safeApiCall {
                api.getBooks()
            }.also {
                it.data?.let { it1 ->
                    localData.getDao().insertAll(it1)
                }
            }
        }.join()
    }

    fun getAuthorsData() = flow {
        emit(safeApiCall {
            api.getAllAuthors()
        })
    }.flowOn(Dispatchers.IO)

     fun deleteData() {
        localData.getDao().delete()
    }

    fun destroyScope(){
        externalScope.cancel()
    }
}