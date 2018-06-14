package com.buur.frederikapp.fragments.champions.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.buur.frederikapp.R
import com.buur.frederikapp.devutility.ImageLoader
import com.buur.frederikapp.devutility.extensions.addChampionImagePath
import com.buur.frederikapp.models.Champion
import com.buur.frederikapp.models.Version
import io.realm.Realm
import kotlinx.android.synthetic.main.view_champion_list_row.view.*

class ChampionRowView : FrameLayout {

    constructor(context: Context) : super(context) {
        inflateView()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun inflateView() {
        View.inflate(context, R.layout.view_champion_list_row,this)
    }

    fun setup(champion: Champion?, context: Context) {
        championListItemTitle.text = champion?.name
        championListItemContent.text = champion?.title
        Realm.getDefaultInstance().use {realm ->
            realm.where(Version::class.java).findFirst()?.version?.let {
                val championImg = champion?.image?.full?.addChampionImagePath(it)
                ImageLoader.InsertImage(context, testImage, championImg)
            }
        }
    }
}