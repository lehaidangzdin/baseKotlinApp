package com.lhd.androidbase.base.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object RecycleViewBinding {
    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(img: ImageView, url: String?) {
        Glide.with(img.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .override(500, 500)
            .into(img)
    }
}