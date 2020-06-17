package com.maxfin.kvartirkatest.presenters

import android.os.Handler
import com.maxfin.kvartirkatest.MainActivity
import com.maxfin.kvartirkatest.api.TownApi
import com.maxfin.kvartirkatest.presenters.base.BasePresenter

class TownPresenter(private val mainActivity: MainActivity) : BasePresenter<ITownView>() {

    fun getTown(longitude: String, latitude: String) {
        val handler = Handler()
        Thread(Runnable {
            val data = TownApi().determineByCoordinate(longitude, latitude)
            handler.post {
                view?.setTown(data)
            }
        }).start()
    }


}