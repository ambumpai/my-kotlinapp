package com.app.kotlin.kotlintest.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View


import com.app.kotlin.kotlintest.R
import com.app.kotlin.kotlintest.databinding.ActivityHomeBinding
import com.app.kotlin.kotlintest.view.adapter.ListViewAdapter
import com.app.kotlin.kotlintest.viewmodel.HomeActivityViewModel


class HomeActivity : AppCompatActivity()
{
    private lateinit var mListViewAdapter: ListViewAdapter
    private lateinit var mHomeActivityViewModel: HomeActivityViewModel
    private lateinit var mHomeActivityBinding: ActivityHomeBinding
    private var aTag="HomeActivity"

    enum class AlertDialogTypeEnum
    {
        NETWORK_ERROR, APP_EXIT_CONFRM
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        try {

            mHomeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
            mHomeActivityViewModel = ViewModelProviders.of(this).get(HomeActivityViewModel::class.java)
            mHomeActivityBinding.viewModel = mHomeActivityViewModel
            mHomeActivityBinding.lifecycleOwner = this
            initializeViews()      // initilizing views
            initializeObservers()  // observes the changes in data

        }catch (e : Exception)
        {
            Log.e("$aTag  onCreate",e.toString())
        }
    }
    private fun initializeViews()
    {
        try {
             mListViewAdapter = ListViewAdapter()
             mHomeActivityBinding.datalistView.adapter = mListViewAdapter

        }catch (e: Exception)
        {
            Log.e("$aTag  initializeViews",e.toString())
        }
    }
    private fun initializeObservers()
    {
        try {
                mHomeActivityViewModel.fetchCountriesFromServer(this, false).observe(this, Observer { kt ->
                    try {
                        this.supportActionBar?.title=kt?.title
                        mListViewAdapter.setAppList(kt!!.rows)
                    }  catch (e: Exception)
                    {
                        Log.e("$aTag  initializeObservers-2",e.toString())
                    }
                })
                mHomeActivityViewModel.mShowProgressBar.observe(this, Observer { bt ->
                    if (bt==true)
                    {
                        mHomeActivityBinding.progressBar.visibility = View.VISIBLE
                        mHomeActivityBinding.floatingActionButton.visibility=View.GONE
                    } else
                    {
                        mHomeActivityBinding.progressBar.visibility = View.GONE
                        mHomeActivityBinding.floatingActionButton.visibility=View.VISIBLE
                    }
                })
                mHomeActivityViewModel.mShowNetworkError.observe(this, Observer { bt ->
                    if (bt==true)
                    {
                        showAlertDialog(AlertDialogTypeEnum.NETWORK_ERROR);  // showing alert dialog if there is not internet connection
                    }
                })
        }catch (e: Exception)
        {
            Log.e("$aTag  initializeObservers",e.toString())
        }
    }

    private fun showAlertDialog(alertType : AlertDialogTypeEnum)
    {
        try {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert")
            if(alertType==AlertDialogTypeEnum.NETWORK_ERROR)
            {
                builder.setMessage("You are not connected to a network.Please establish an internet connection and try again")
                builder.setPositiveButton(R.string.dialog_ok) { dialog, which ->
                    dialog.dismiss()
                    finish()
                }
            }else if(alertType==AlertDialogTypeEnum.APP_EXIT_CONFRM)
            {
                builder.setMessage("Do you want to exit that app?")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    dialog.dismiss()
                    finish()
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->

                    dialog.dismiss()
                }
            }
            builder.show()

        }catch (e : Exception)
        {
            Log.e("$aTag  showAlertDialog",e.toString())
        }
    }
    override fun onBackPressed()
    {
        showAlertDialog(AlertDialogTypeEnum.APP_EXIT_CONFRM)
    }
}