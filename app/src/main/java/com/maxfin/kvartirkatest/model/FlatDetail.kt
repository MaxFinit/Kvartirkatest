package com.maxfin.kvartirkatest.model

import java.io.Serializable

data class FlatDetail(
    val id: String,
    val title: String,
    val price: String,
    val currency: String,
    val address: String,
    val rooms: String,
    val building_type: String,
    val description: String,
    val description_full: String,
    val photos: MutableList<String>?


) : Serializable