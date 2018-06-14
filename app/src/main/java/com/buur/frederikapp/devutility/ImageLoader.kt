package com.buur.frederikapp.devutility

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {

    fun InsertImage(context: Context?, view: ImageView?, image: Any?) {

        if (context == null || view == null || image == null) return

        if (context is Activity && context.isDestroyed) return

        Glide.with(context)
                .load(image)
                .into(view)

    }

}