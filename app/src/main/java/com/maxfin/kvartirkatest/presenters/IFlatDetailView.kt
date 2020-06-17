package com.maxfin.kvartirkatest.presenters

import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.base.IBaseView

interface IFlatDetailView: IBaseView {
    fun setDetails(flats:ItemModel)
    fun onProgressOn()
    fun onProgressOff()
}