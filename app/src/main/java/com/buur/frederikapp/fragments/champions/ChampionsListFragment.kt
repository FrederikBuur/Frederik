package com.buur.frederikapp.fragments.champions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buur.frederikapp.R
import com.buur.frederikapp.fragments.FredFragment
import io.reactivex.schedulers.Schedulers

class ChampionsListFragment : FredFragment() {

    private var championsController: ChampionsController? = null

    private val controller: ChampionsController
        get() {
            return championsController?.let { it } ?: ChampionsController()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_champion_list, container, false)

        fetchChampions()

        return view
    }

    private fun fetchChampions() {

            controller.fetchChampionList()
                    .subscribeOn(Schedulers.io())
                    .doFinally {
                        context?.let { con ->
                            mainActivity?.makeToast(con, "Champions have been fetched")
                        }
                    }
                    .subscribe { championList ->
                        // save into realm
                    }
    }

}