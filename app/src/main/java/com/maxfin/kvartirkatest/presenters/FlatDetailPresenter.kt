package com.maxfin.kvartirkatest.presenters

import android.os.Handler
import com.maxfin.kvartirkatest.FlatsActivity
import com.maxfin.kvartirkatest.FlatsDetailActivity
import com.maxfin.kvartirkatest.api.FlatsApi
import com.maxfin.kvartirkatest.api.FlatsDetailApi
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.base.BasePresenter

class FlatDetailPresenter(private val mainActivity: FlatsDetailActivity) : BasePresenter<IFlatDetailView>() {

    fun getFlats(idFlat: String?) {
        val handler = Handler()
        Thread(Runnable {
            showProgress(true)
            val data = FlatsDetailApi().getFlats(idFlat)
            handler.post {
                showProgress(false)
                    view?.setDetails(ItemModel(0,data,0))
            }
        }).start()
    }

    private fun showProgress(show: Boolean) {
        if (show)
            view?.onProgressOn()
        else
            view?.onProgressOff()
    }


}