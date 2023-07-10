package com.lhd.androidbase.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lhd.androidbase.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.home_spaceship_launch)
        .override(100, 200)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}


