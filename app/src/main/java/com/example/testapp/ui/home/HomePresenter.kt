package com.example.testapp.ui.home

import com.example.testapp.base.BasePresenter

class HomePresenter<V:HomeMvpView>():BasePresenter<V>(),HomeMvpPresenter{

    override fun getData() {
        println("Here in presenter")
        getMvpView()?.onDataSuccess(true);
    }
}