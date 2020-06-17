package com.maxfin.kvartirkatest.presenters

import com.maxfin.kvartirkatest.model.Flat
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.base.IBaseView

interface IFlatView: IBaseView {
    fun setFlats(flats:MutableList<ItemModel>)
    fun addFlats(flats:MutableList<ItemModel>)
    fun goToDetail(flatsId:String)
    fun onProgressOn()
    fun onProgressOff()
}