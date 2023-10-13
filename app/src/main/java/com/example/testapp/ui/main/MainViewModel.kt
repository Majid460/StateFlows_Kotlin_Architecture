package com.example.testapp.ui.main

import androidx.lifecycle.viewModelScope
import com.example.testapp.base.BaseViewModel
import com.example.testapp.data.Repository
import com.example.testapp.data.models.authors.AuthorsResponseModel
import com.example.testapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : BaseViewModel() {
    private val authorsData =
        MutableStateFlow<NetworkResult<MutableList<AuthorsResponseModel>?>>(NetworkResult.loading())
    val authorsStateFlow: StateFlow<NetworkResult<MutableList<AuthorsResponseModel>?>> = authorsData


    val booksStateFlow = repo.getBooksLocally()

    fun deleteBooks() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteData()
    }
    fun getBooks() = viewModelScope.launch(Dispatchers.IO) {
        repo.getBooksData()
    }

    fun getAllAuthors() {
        viewModelScope.launch {
            repo.getAuthorsData().catch {
                authorsData.value = it.message?.let { it1 -> NetworkResult.error(it1) }!!
            }.collect {
                if (it.data != null) {
                    authorsData.value = NetworkResult.success(it.data)
                } else {
                    authorsData.value = it.message?.let { it1 -> NetworkResult.error(it1) }!!
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        repo.destroyScope()
    }
}