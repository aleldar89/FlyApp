package com.example.airtickets_feature

import com.example.common_resources.R
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


fun ImageView.loadImage(@DrawableRes resId: Int) {
    val transformations = mutableListOf<BitmapTransformation>()
    transformations.add(CenterCrop())
    transformations.add(RoundedCorners(resources.getDimensionPixelSize(R.dimen.corner_radius_8)))

    Glide.with(this)
        .load(resId)
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.image_placeholder)
        .transform(*transformations.toTypedArray())
        .timeout(30_000)
        .into(this)
}
