package com.app.kotlin.kotlintest.model


import java.io.Serializable
data class CountryData (
    var title: String? ,
    var description: String? ,
    var imageHref: String?
) : Serializable