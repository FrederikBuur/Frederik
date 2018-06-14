package com.buur.frederikapp.fragments.summoner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.buur.frederikapp.R
import com.buur.frederikapp.controllers.SessionController
import com.buur.frederikapp.controllers.SummonerController
import com.buur.frederikapp.devutility.ImageLoader
import com.buur.frederikapp.devutility.extensions.addProfileIconImagePath
import com.buur.frederikapp.fragments.FredFragment
import com.buur.frederikapp.models.Version
import com.buur.frederikapp.modelsapi.match.MatchResponse
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_summoner.*

class SummonerFragment : FredFragment() {

    private var summonerController: SummonerController? = null

    private var adapter: SummonerMatchlistAdapter? = null
    private var matchList: ArrayList<MatchResponse>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summoner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()

    }

    private fun setup() {

        matchList = SessionController.getInstance().summonerSearchResult?.matchList
        context?.let { adapter = SummonerMatchlistAdapter(it, matchList) }
        matchesRecyclerView.adapter = adapter

        setupSummonerInfo()
        setupSummonerMatchlist()

    }

    private fun setupSummonerInfo() {

        val summoner = SessionController.getInstance().summonerSearchResult?.summoner

        summonerName.text = summoner?.name
        summonerLevel.text = "Level: ${summoner?.summonerLevel?.toString()}"

        // loads summonerSearchResult icon
        context?.let { con ->
            Realm.getDefaultInstance().use { realm ->
                Version.getLocalVersion(realm)?.let {
                    val profileIconPath = summoner?.profileIconId.toString().addProfileIconImagePath(it)
                    ImageLoader.InsertImage(con, summonerIcon, profileIconPath)
                }
            }
        }
    }

    private fun setupSummonerMatchlist() {

        // load more when scrolling down

    }
}
