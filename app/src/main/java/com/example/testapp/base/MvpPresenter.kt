package com.example.testapp.base

interface MvpPresenter<V: MvpView> {
    fun onAttach(mvpView: V)
    fun onDetach()
}