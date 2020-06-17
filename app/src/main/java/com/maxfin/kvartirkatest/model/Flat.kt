package com.maxfin.kvartirkatest.model

import java.io.Serializable

data class Flat(
    val id: String,
    val name: String,
    val photo: String,
    val price: String,
    val currency: String
) : Serializable