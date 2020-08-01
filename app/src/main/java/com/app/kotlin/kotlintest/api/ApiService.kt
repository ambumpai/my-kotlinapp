package com.app.kotlin.kotlintest.api


import com.app.kotlin.kotlintest.model.Country
import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService
{
    @GET("facts.json")
    fun getCountry(): Call<Country>
}