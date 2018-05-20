package com.buur.frederikapp.fragments.champions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buur.frederikapp.R
import com.buur.frederikapp.api.ErrorHandler
import com.buur.frederikapp.fragments.FredFragment
import com.buur.frederikapp.models.Champion
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_champion_list.*
import java.util.*

class ChampionsListFragment : FredFragment() {

    private var championsController: ChampionsController? = null
    private var adapter: ChampionListAdapter? = null
    private var championList: ArrayList<Champion>? = null
    private var championListFromRealm: List<Champion>? = null
    private var realm: Realm? = null


    private val controller: ChampionsController
        get() {
            return championsController?.let { it } ?: ChampionsController()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_champion_list, container, false)

        // fetch if sessioncontroller.championList is empty TODO
        fetchChampions()

        return view
    }

    override fun onStart() {
        super.onStart()
        setup()
    }

    private fun setup() {
        if (championList == null) {
            championList = ArrayList()
        }
        context?.let {
            if (adapter == null) {
                adapter = ChampionListAdapter(it, championList)
            }
            championListRecyclerView?.adapter = adapter
        }
    }

    private fun fetchChampions() {
        controller.fetchChampionList()
//                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    readChampionList()
                }
                .subscribe({ championListResponse ->
                    championList?.addAll(championListResponse.data.values.toList())
                    adapter?.notifyDataSetChanged()
                    context?.let { con ->
                        mainActivity?.makeToast(con, "Champions have been fetched")
                    }
                }, {
                    mainActivity?.makeToast(context, ErrorHandler.handleError(it))
                })
    }

    private fun readChampionList() {
        realm = Realm.getDefaultInstance()

        realm?.executeTransaction {  innerRealm ->

            val realmResult = realm?.where(Champion::class.java)?.sort("name")?.findAll()

            realmResult?.let {
                championListFromRealm = innerRealm.copyFromRealm(realmResult)
            }


            championListFromRealm?.let { championList?.addAll(it) }
            adapter?.notifyDataSetChanged()

        }

    }

}