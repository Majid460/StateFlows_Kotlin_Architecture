package com.example.testapp.base

open class BasePresenter<V : MvpView> : MvpPresenter<V> {
    private var mvpView: V? = null
    override fun onAttach(mvpView: V) {
       this.mvpView=mvpView
    }

    override fun onDetach() {
      this.mvpView=null
    }
    fun getMvpView(): V? {
        return mvpView
    }
}