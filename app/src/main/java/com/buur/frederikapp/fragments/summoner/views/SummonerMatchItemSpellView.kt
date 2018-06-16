package com.buur.frederikapp.fragments.summoner.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.buur.frederikapp.R
import com.buur.frederikapp.devutility.ImageLoader
import com.buur.frederikapp.devutility.extensions.addItemIconImagePath
import com.buur.frederikapp.devutility.extensions.addSummonerSpellIconImagePath
import com.buur.frederikapp.models.SummonerSpell
import com.buur.frederikapp.models.Version
import com.buur.frederikapp.modelsapi.match.Participant
import io.realm.Realm
import kotlinx.android.synthetic.main.view_summoner_match_item_spell.view.*

class SummonerMatchItemSpellView : RelativeLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_summoner_match_item_spell, this)
    }

    fun setupItemSpellView(participant: Participant?) {

        Realm.getDefaultInstance().use { realm ->
            Version.getLocalVersion(realm)?.let { version ->

                val spellOneKey = SummonerSpell.getSummonerSpellKey(realm, participant?.spell1Id)
                val spellOneUrl = spellOneKey?.addSummonerSpellIconImagePath(version)

                val spellTwoKey = SummonerSpell.getSummonerSpellKey(realm, participant?.spell2Id)
                val spellTwoUrl = spellTwoKey?.addSummonerSpellIconImagePath(version)

                val item0 = participant?.stats?.item0.toString().addItemIconImagePath(version)
                val item1 = participant?.stats?.item1.toString().addItemIconImagePath(version)
                val item2 = participant?.stats?.item2.toString().addItemIconImagePath(version)
                val item3 = participant?.stats?.item3.toString().addItemIconImagePath(version)
                val item4 = participant?.stats?.item4.toString().addItemIconImagePath(version)
                val item5 = participant?.stats?.item5.toString().addItemIconImagePath(version)
                val item6 = participant?.stats?.item6.toString().addItemIconImagePath(version)

                ImageLoader.InsertImage(context, championSpell1, spellOneUrl)
                ImageLoader.InsertImage(context, championSpell2, spellTwoUrl)

                ImageLoader.InsertImage(context, championItem0, item0)
                ImageLoader.InsertImage(context, championItem1, item1)
                ImageLoader.InsertImage(context, championItem2, item2)
                ImageLoader.InsertImage(context, championItem3, item3)
                ImageLoader.InsertImage(context, championItem4, item4)
                ImageLoader.InsertImage(context, championItem5, item5)
                ImageLoader.InsertImage(context, championItem6, item6)

            }
        }
    }
}