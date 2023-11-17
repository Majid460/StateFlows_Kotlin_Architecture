package com.example.testapp.ui.mvi

import com.example.testapp.data.models.books.BooksResponse


sealed class ViewState{
    object Idle : ViewState()
    object Loading : ViewState()
    data class Books(val books: MutableList<BooksResponse>) : ViewState()
    data class Error(val error: String?) : ViewState()
}
