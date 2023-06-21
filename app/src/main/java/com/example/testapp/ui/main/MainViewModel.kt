package com.example.testapp.ui.main

import androidx.lifecycle.viewModelScope
import com.example.testapp.base.BaseViewModel
import com.example.testapp.data.Repository
import com.example.testapp.data.models.authors.AuthorsResponseModel
import com.example.testapp.data.models.books.BooksResponse
import com.example.testapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo:Repository): BaseViewModel() {
 private val mutableStateData= MutableStateFlow<NetworkResult<MutableList<BooksResponse>?>>(NetworkResult.loading())
 private val authorsData= MutableStateFlow<NetworkResult<MutableList<AuthorsResponseModel>?>>(NetworkResult.loading())
   val booksStateFlow:StateFlow<NetworkResult<MutableList<BooksResponse>?>> = mutableStateData
   val authorsStateFlow:StateFlow<NetworkResult<MutableList<AuthorsResponseModel>?>> = authorsData

    fun getBooks(){
        viewModelScope.launch {
            repo.getBooksData().catch {
                mutableStateData.value= it.message?.let { it1 -> NetworkResult.error(it1) }!!
            }.collect{
                if(it.data != null){

                    mutableStateData.value=NetworkResult.success(it.data)
                }else{
                    mutableStateData.value= it.message?.let { it1 -> NetworkResult.error(it1) }!!

                }
            }
        }
    }

    fun getAllAuthors(){
        viewModelScope.launch {
            repo.getAuthorsData().catch {
             authorsData.value= it.message?.let { it1 -> NetworkResult.error(it1) }!!
            }.collect{
             if(it.data!=null){
                 authorsData.value= NetworkResult.success(it.data)
             }
             else{
                 authorsData.value= it.message?.let { it1 -> NetworkResult.error(it1) }!!
             }
            }
        }
    }

}