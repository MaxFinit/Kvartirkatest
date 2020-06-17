package com.maxfin.kvartirkatest.model.base

open class ItemModel {
    constructor()

    constructor(viewType: Int, data: Any, id: Int) {
        this.viewType = viewType
        this.data = data
        this.id = id
    }

    internal var viewType: Int = 0
    internal var data: Any? = null
    internal var id: Int = 0
}