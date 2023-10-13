package com.example.testapp.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object ImageUtils {

    fun loadImageWithRoundedCorners(
        imageView: ImageView, url: String?, @DrawableRes placeholder: Int,
        roundCorners: Float
    ) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .transform(
                CenterCrop(),
                RoundedCorners(roundCorners.toInt())
            )
            .into(imageView)
    }
}