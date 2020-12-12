package com.isyed.samples

import java.util.*

data class MenuItem(
    var name : String,
    var description : String,
    var price : Double,
    var image_url : String,
    var id : String  = UUID.randomUUID().toString()
)