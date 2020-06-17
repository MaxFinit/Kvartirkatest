package com.maxfin.kvartirkatest.presenters.base

abstract class BasePresenter<T : IBaseView> : IBasePresenterView<T> {

    var view: T? = null
        private set

    override fun attachView(mvpView: T?) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {
        TODO("Not yet implemented")
    }

    override fun viewIsReady() {
        TODO("Not yet implemented")
    }

    protected open fun isViewAttached(): Boolean {
        return view != null
    }

}