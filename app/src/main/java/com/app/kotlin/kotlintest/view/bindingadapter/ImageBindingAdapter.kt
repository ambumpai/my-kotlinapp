package com.app.kotlin.kotlintest.view.bindingadapter

import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.app.kotlin.kotlintest.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object ImageBindingAdapter {

    @BindingAdapter("app:imageThumb")
    @JvmStatic
    fun setImageToImageView(aImageView: ImageView, ImageResource: String?)
    {

        try {

            val aContext = aImageView.context
            val reqOp =RequestOptions()
            reqOp.placeholder(R.drawable.error)
            reqOp.error(R.drawable.error)
            reqOp.diskCacheStrategy(DiskCacheStrategy.DATA)
            Glide.with(aContext).load(ImageResource).apply(reqOp).into(aImageView)

        }catch (e : Exception)
        {
            Log.e("ImageBindingAdapter",e.toString())
        }

    }
}