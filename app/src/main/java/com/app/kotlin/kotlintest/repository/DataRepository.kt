package com.app.kotlin.kotlintest.repository

import android.arch.lifecycle.MutableLiveData
import com.app.kotlin.kotlintest.api.RestClient
import com.app.kotlin.kotlintest.interfaces.NetworkResponseCallback
import com.app.kotlin.kotlintest.model.Country

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository private constructor()
{
    private lateinit var mCallback: NetworkResponseCallback
    private var mCountryList= MutableLiveData<Country>()

    companion object
    {
        private var mInstance: DataRepository? = null
        fun getInstance(): DataRepository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = DataRepository()
                }
            }
            return mInstance!!
        }
    }

    private lateinit var mCountryCall: Call<Country>

    fun getCountries(callback: NetworkResponseCallback, forceFetch : Boolean): MutableLiveData<Country>
    {
        mCallback = callback

        if (mCountryList.value!=null && !forceFetch)
        {
            mCallback.onNetworkSuccess()
            return mCountryList
        }

        mCountryCall = RestClient.getInstance().getApiService().getCountry()
        mCountryCall.enqueue(object : Callback<Country>
        {

            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                mCountryList.value = response.body()
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<Country>, t: Throwable) {
                mCountryList.value = null
                mCallback.onNetworkFailure(t)
            }

        })

        return mCountryList
    }
}