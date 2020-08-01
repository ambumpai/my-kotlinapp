package com.app.kotlin.kotlintest.model

import java.io.Serializable

data class Country(
    var title: String ,
    var rows: ArrayList<CountryData>
) : Serializable

