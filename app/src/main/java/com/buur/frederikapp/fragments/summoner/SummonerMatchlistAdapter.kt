package com.buur.frederikapp.fragments.summoner

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.buur.frederikapp.fragments.summoner.views.SummonerMatchRowView
import com.buur.frederikapp.fragments.summoner.views.SummonerMatchProgressView
import com.buur.frederikapp.fragments.summoner.views.SummonerSuperRowView
import com.buur.frederikapp.modelsapi.match.MatchResponse

class SummonerMatchlistAdapter(var context: Context, var matchList: ArrayList<MatchResponse>?) : RecyclerView.Adapter<SummonerMatchlistAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {

        return MatchViewHolder(when(viewType) {
            MatchlistItemType.Match.ordinal -> SummonerMatchRowView(context)
            else -> SummonerMatchProgressView(context)
        })

    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        val itemView = holder.itemView
        val match = getMatch(position)

        when(itemView) {
            is SummonerMatchRowView -> itemView.setup(match)
            else -> {
                itemView
            }
        }

    }

    private fun getMatch(position: Int) : MatchResponse? {

        return matchList?.getOrNull(position)

    }

    override fun getItemViewType(position: Int): Int {
        return when {
            matchList?.getOrNull(position) != null -> MatchlistItemType.Match.ordinal
            else -> MatchlistItemType.Progress.ordinal
        }
    }

    override fun getItemCount(): Int {
        return matchList?.size ?: 0
    }

    inner class MatchViewHolder(container: SummonerSuperRowView) : RecyclerView.ViewHolder(container) {
        init {
        }
    }

    enum class MatchlistItemType {
        Match, Progress, Ad
    }

}