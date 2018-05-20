package com.buur.frederikapp.fragments.summoner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.buur.frederikapp.R
import com.buur.frederikapp.controllers.SessionController
import com.buur.frederikapp.controllers.SummonerController
import com.buur.frederikapp.extensions.addProfileIconImagePath
import com.buur.frederikapp.fragments.FredFragment
import kotlinx.android.synthetic.main.fragment_summoner.*

class SummonerFragment : FredFragment() {

    private var summonerController: SummonerController? = null

    private fun getController(): SummonerController {
        return if (summonerController == null) {
            SummonerController()
        } else {
            summonerController!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summoner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()

    }

    private fun setup() {

        setupSummonerInfo()

    }

    private fun setupSummonerInfo() {

        val summoner = SessionController.getInstance().summoner

        summonerName.text = summoner?.name
        summonerLevel.text = "Level: ${summoner?.summonerLevel?.toString()}"

        // loads summoner icon
        context?.let {
            val profileIconPath = summoner?.profileIconId.toString().addProfileIconImagePath()
            Glide.with(it)
                    .load(profileIconPath)
                    .into(summonerIcon)
        }

    }

}
