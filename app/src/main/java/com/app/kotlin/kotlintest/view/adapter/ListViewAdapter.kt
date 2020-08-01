package com.app.kotlin.kotlintest.view.adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.app.kotlin.kotlintest.R
import com.app.kotlin.kotlintest.databinding.ListviewControlBinding
import com.app.kotlin.kotlintest.model.CountryData


class ListViewAdapter () : BaseAdapter()
{
    private var mCountryDataList = ArrayList<CountryData>()

    fun setAppList(countryData: ArrayList<CountryData>)
    {
        mCountryDataList=reMoveNullValueVal(countryData)
        notifyDataSetChanged()
    }
    override fun getCount(): Int = mCountryDataList.size

    override fun getItem(position: Int): Any? = mCountryDataList[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        val binding: ListviewControlBinding
        if (convertView == null)
        {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.listview_control, parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ListviewControlBinding
        }
        binding?.countrydata = getItem(position) as CountryData
        return binding.root
    }
    private fun reMoveNullValueVal(mCountryDataArraylist : ArrayList<CountryData>) : ArrayList<CountryData>
    {
        var mCountryDataList = ArrayList<CountryData>()
        if(mCountryDataArraylist.size>0)
        {
            for(CountryData in mCountryDataArraylist)
            {
                if(CountryData.title!=null || CountryData.imageHref!=null || CountryData.description!=null)
                {
                    mCountryDataList.add(CountryData)
                }
            }
        }
        return mCountryDataList
    }
}