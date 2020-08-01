package com.app.kotlin.kotlintest.interfaces

interface NetworkResponseCallback
{
    fun onNetworkSuccess()
    fun onNetworkFailure(th : Throwable)
}