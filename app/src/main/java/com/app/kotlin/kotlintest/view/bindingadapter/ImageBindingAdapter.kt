package com.app.kotlin.kotlintest.view.bindingadapter

import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.app.kotlin.kotlintest.R
import com.bumptech.glide.request.RequestOptions


object ImageBindingAdapter {

    @BindingAdapter("app:imageThumb")
    @JvmStatic
    fun setImageToImageView(aImageView: ImageView, ImageResource: String?)
    {
        val aContext = aImageView.context
        val reqOp =RequestOptions()
        reqOp.placeholder(R.drawable.error)
        reqOp.error(R.drawable.error)
        Glide.with(aContext).setDefaultRequestOptions(reqOp).load(ImageResource).into(aImageView)
    }
}