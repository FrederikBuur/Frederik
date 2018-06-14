package com.buur.frederikapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.buur.frederikapp.R
import com.buur.frederikapp.models.Champion
import kotlinx.android.synthetic.main.view_champion_list_row.view.*

class SharedProgressView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setup() {
        View.inflate(context, R.layout.view_shared_progress,this)
    }
}