package com.buur.frederikapp.fragments.startup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buur.frederikapp.R
import com.buur.frederikapp.controllers.StaticDataController
import com.buur.frederikapp.fragments.FredFragment
import com.buur.frederikapp.fragments.search.SearchFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function4
import io.reactivex.rxkotlin.Observables

class StartUpFragment : FredFragment() {

    private var championsController: StaticDataController? = null
    private val staticDataController: StaticDataController
        get() {
            return championsController?.let { it } ?: StaticDataController()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fetchStaticData()

        // show fun loading animation

    }


    private fun fetchStaticData() {
        val version = staticDataController.getVersions()
        val champions = staticDataController.fetchChampions()
        val items = staticDataController.fetchItemData()
        val summonerSpells = staticDataController.fetchSummonerSpellData()

        val jobs = ArrayList<Observable<*>>()

        jobs.add(version)
        jobs.add(champions)
        jobs.add(items)
        jobs.add(summonerSpells)

        Observable.zip(jobs, { _ -> })
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable.empty())
                .doFinally {
                    mainActivity?.navigateToFragment(SearchFragment(), shouldAddToBackStack = false)
                }
                .subscribe({
                    Log.d("StartUpFragment", "All static data done")
                }, { _ ->
                })

    }
}