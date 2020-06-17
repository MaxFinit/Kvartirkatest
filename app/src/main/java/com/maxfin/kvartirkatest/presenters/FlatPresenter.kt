package com.maxfin.kvartirkatest.presenters

import android.os.Handler
import com.maxfin.kvartirkatest.FlatsActivity
import com.maxfin.kvartirkatest.api.FlatsApi
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.base.BasePresenter

class FlatPresenter(private val mainActivity: FlatsActivity) : BasePresenter<IFlatView>() {

    public var idTown: String? = ""

    fun getFlats(idTown: String?, offset: String,update:Boolean) {
        val handler = Handler()
        Thread(Runnable {
            showProgress(true)
            this.idTown = idTown
            val name = FlatsApi().getFlats(idTown, offset)
            handler.post {
                showProgress(false)
                val list = mutableListOf<ItemModel>()
                for (item in name)
                    list.add(ItemModel(0, item, 0))
                if(!update)
                view?.setFlats(list)
                else
                    view?.addFlats(list)
            }
        }).start()
    }

    private fun showProgress(show: Boolean) {
        if (show)
            view?.onProgressOn()
        else
            view?.onProgressOff()
    }

    fun goToDetail(id: String) {
       view?.goToDetail(id)
    }


}