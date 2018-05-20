package com.buur.frederikapp.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buur.frederikapp.R
import com.buur.frederikapp.controllers.SessionController
import com.buur.frederikapp.controllers.SummonerController
import com.buur.frederikapp.fragments.FredFragment
import com.buur.frederikapp.fragments.champions.ChampionsListFragment
import com.buur.frederikapp.fragments.summoner.SummonerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.regex.Pattern

class SearchFragment : FredFragment(), View.OnClickListener {

    private var summonerController: SummonerController? = null

    private fun getController(): SummonerController {
        return if (summonerController == null) {
            SummonerController()
        } else {
            summonerController!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
                              , savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonChampionDatabase.setOnClickListener(this)
        buttonSearch.setOnClickListener(this)

    }

    private fun searchSummonerFlow() {

        val searchInput = searchInputContainer.editText?.text?.toString()?.trim()

        searchInput?.let { input ->
            if (isNameValid(input)) {
                getController().fetchSummoner(input)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            mainActivity?.navigateToFragment(SummonerFragment())
                        }, {})
            } else {
                mainActivity?.makeToast(context, "Invalid search")
            }
        }
    }

    private fun isNameValid(name: String?) : Boolean {

        val regex = "^[0-9\\p{L} _]+\$"

        return Pattern.matches(regex, name)
    }

    override fun onClick(v: View?) {

        when(v) {
            buttonChampionDatabase -> {
                mainActivity?.navigateToFragment(ChampionsListFragment())
            }
            buttonSearch -> {
               searchSummonerFlow()
            }
        }

    }
}