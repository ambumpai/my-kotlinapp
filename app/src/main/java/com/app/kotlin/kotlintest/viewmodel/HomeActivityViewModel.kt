package com.app.kotlin.kotlintest.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View

import com.app.kotlin.kotlintest.interfaces.NetworkResponseCallback
import com.app.kotlin.kotlintest.model.Country
import com.app.kotlin.kotlintest.repository.DataRepository
import com.app.kotlin.kotlintest.util.NetworkHelper

class HomeActivityViewModel : ViewModel()
{
    private var mList : MutableLiveData<Country> =MutableLiveData()
    var mShowProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    var mShowNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var mShowApiError: MutableLiveData<Boolean> = MutableLiveData()
    private var mRepository = DataRepository.getInstance()

    fun fetchCountriesFromServer(context: Context, forceFetch : Boolean): MutableLiveData<Country>
    {
        if (NetworkHelper.isOnline(context))
        {
            mShowProgressBar.value = true
            mList = mRepository.getCountries(object : NetworkResponseCallback
            {
                override fun onNetworkFailure(th: Throwable)
                {
                    mShowApiError.value = true
                }

                override fun onNetworkSuccess()
                {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }

        return mList
    }

    fun onRefreshClicked(view : View)
    {
        fetchCountriesFromServer(view.context, true)
    }
}