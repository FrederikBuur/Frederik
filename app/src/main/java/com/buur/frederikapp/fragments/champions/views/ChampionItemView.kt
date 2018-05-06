package com.buur.frederikapp.fragments.champions.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.buur.frederikapp.R
import com.buur.frederikapp.models.Champion
import kotlinx.android.synthetic.main.view_champion_list_item.view.*

class ChampionItemView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setup(champion: Champion?, context: Context) {
        View.inflate(context, R.layout.view_champion_list_item,this)

        championListItemTitle.text = champion?.name
        championListItemContent.text = champion?.title
        Glide.with(context)
                .load("http://ddragon.leagueoflegends.com/cdn/7.5.2/img/champion/" + champion?.image?.full)
                .into(testImage)

    }
}