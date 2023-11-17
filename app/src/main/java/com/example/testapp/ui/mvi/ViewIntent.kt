package com.example.testapp.ui.mvi

sealed class ViewIntent{
    object FetchBooks: ViewIntent()
}
