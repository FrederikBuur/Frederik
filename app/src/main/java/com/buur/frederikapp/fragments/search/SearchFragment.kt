package com.buur.frederikapp.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buur.frederikapp.R
import com.buur.frederikapp.fragments.FredFragment
import com.buur.frederikapp.fragments.champions.ChampionsListFragment
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : FredFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        searchButtonChampionDatabase.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v) {
            searchButtonChampionDatabase -> {
                mainActivity?.navigateToFragment(ChampionsListFragment())
            }
        }

    }
}