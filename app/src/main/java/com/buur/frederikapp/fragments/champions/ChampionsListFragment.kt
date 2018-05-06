package com.buur.frederikapp.fragments.champions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buur.frederikapp.R
import com.buur.frederikapp.fragments.FredFragment
import com.buur.frederikapp.models.Champion
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_champion_list.*

class ChampionsListFragment : FredFragment() {

    private var championsController: ChampionsController? = null
    private var adapter: ChampionListAdapter? = null
    private var championList: ArrayList<Champion>? = null

    private val controller: ChampionsController
        get() {
            return championsController?.let { it } ?: ChampionsController()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_champion_list, container, false)

        fetchChampions()

        return view
    }

    override fun onStart() {
        super.onStart()
        setup()
    }

    private fun setup() {
        if (championList == null) { championList = ArrayList() }
        context?.let {
            adapter = ChampionListAdapter(it, championList)
            championListRecyclerView?.adapter = adapter
        }
    }

    private fun fetchChampions() {
        controller.fetchChampionList()
                .subscribeOn(Schedulers.io())
                .doFinally {
                    context?.let { con ->
                        mainActivity?.makeToast(con, "Champions have been fetched")
                    }
                }
                .subscribe { championListResponse ->
                    // save into realm
                    championList?.addAll(championListResponse.data.values.toList())
                    adapter?.notifyDataSetChanged()
                }
    }

}