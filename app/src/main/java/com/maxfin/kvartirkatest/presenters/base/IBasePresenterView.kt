package com.maxfin.kvartirkatest.presenters.base

interface IBasePresenterView<V : IBaseView> {
    fun attachView(mvpView: V?)

    fun viewIsReady()

    fun detachView()

    fun destroy()

}