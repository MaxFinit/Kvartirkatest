package com.maxfin.kvartirkatest.presenters

import com.maxfin.kvartirkatest.model.Location
import com.maxfin.kvartirkatest.presenters.base.IBaseView

interface ITownView: IBaseView {
   fun setTown(name: Location?)
}