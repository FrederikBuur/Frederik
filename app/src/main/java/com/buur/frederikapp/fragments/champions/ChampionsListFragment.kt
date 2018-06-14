package com.buur.frederikapp.fragments.champions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buur.frederikapp.R
import com.buur.frederikapp.controllers.StaticDataController
import com.buur.frederikapp.fragments.FredFragment
import com.buur.frederikapp.models.Champion
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_champion_list.*

class ChampionsListFragment : FredFragment() {

    private var championsController: StaticDataController? = null
    private var adapter: ChampionListAdapter? = null
    private var championList: List<Champion>? = null


    private val controller: StaticDataController
        get() {
            return championsController?.let { it } ?: StaticDataController()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_champion_list, container, false)

        readChampionListAndNotify()

        return view
    }

    override fun onStart() {
        super.onStart()
        setup()
    }

    private fun setup() {
        if (championList == null) {
            Realm.getDefaultInstance().use { realm ->
                val realmChampionList = realm.where(Champion::class.java).sort("name").findAll()

                championList = realm.copyFromRealm(realmChampionList)
            }
        }
        context?.let {
            if (adapter == null) {
                adapter = ChampionListAdapter(it, championList)
            }
            championListRecyclerView?.adapter = adapter
        }
    }

    private fun readChampionListAndNotify() {

        Realm.getDefaultInstance().use { realm ->
            val realmChampionList = realm.where(Champion::class.java).sort("name").findAll()
            championList = realm.copyFromRealm(realmChampionList)
            adapter?.notifyDataSetChanged()
        }

    }

}