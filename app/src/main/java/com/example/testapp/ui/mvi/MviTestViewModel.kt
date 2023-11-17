package com.example.testapp.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MviTestViewModel @Inject constructor(private val repo: Repository)  : ViewModel() {
    val userIntent = Channel<ViewIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<ViewState>(ViewState.Idle)
    val state: StateFlow<ViewState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{
                when(it){
                    is ViewIntent.FetchBooks -> fetchBooks()
                }
            }
        }
    }
    private fun fetchBooks(){
        viewModelScope.launch {
            _state.value = ViewState.Loading
            _state.value = try{
                ViewState.Books(repo.getBooksForMvi())
            }catch (e:Exception){
                ViewState.Error(e.localizedMessage)
            }
        }
    }
}