package com.buur.frederikapp.fragments.champions

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.FrameLayout
import com.buur.frederikapp.fragments.champions.views.ChampionItemView
import com.buur.frederikapp.models.Champion
import com.buur.frederikapp.views.SharedProgressView

class ChampionListAdapter(val context: Context, var championList: List<Champion>?) : RecyclerView.Adapter<ChampionListAdapter.ChampionViewHolder>() {

    enum class ChampionListItemType {
        Champion, Ad
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {

        return ChampionViewHolder(
                when (viewType) {
                    ChampionListItemType.Champion.ordinal -> ChampionItemView(context)
                    ChampionListItemType.Ad.ordinal -> SharedProgressView(context)
                    else -> {
                        SharedProgressView(context) // should never happen
                    }
                }
        )
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {

        val itemView = holder.itemView
        val champion = getChampion(position)

        when (itemView) {
            is ChampionItemView -> itemView.setup(champion, context)
            else -> {
                // should never happen
            }
        }
    }

    override fun getItemCount(): Int {
        return championList?.size ?: 0
    }

    private fun getChampion(position: Int): Champion? {
        return championList?.getOrNull(position)
    }

    inner class ChampionViewHolder(container: FrameLayout) : RecyclerView.ViewHolder(container) {
        init {
        }
    }

}