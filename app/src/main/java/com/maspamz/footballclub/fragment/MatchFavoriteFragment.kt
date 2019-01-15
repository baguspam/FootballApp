package com.maspamz.footballclub.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maspamz.footballclub.DetailActivityMatch
import com.maspamz.footballclub.R
import com.maspamz.footballclub.adapter.MatchFavoriteAdapter
import com.maspamz.footballclub.config.db.database
import com.maspamz.footballclub.config.data.Favorite
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by Maspamz on 12/09/2018.
 *
 */

class MatchFavoriteFragment : Fragment() {

    private var myFavorite: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: MatchFavoriteAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        val activity = activity
        swipeRefreshLayout = rootView.l_swipe_favorite
        //RecycleView plus Adapter
        adapter = MatchFavoriteAdapter(myFavorite) {
            startActivity(intentFor<DetailActivityMatch>(
                    "ID_CLUB1" to it.idClub1,
                    "ID_CLUB2" to it.idClub2,
                    "ID_EVENT" to it.idEvent
            ))
        }

        listTeam = rootView.lrv_view_favorite
        listTeam.layoutManager = LinearLayoutManager(activity)
        listTeam.adapter = adapter

        myFavorite.clear()
        showFavorite()

        swipeRefreshLayout.onRefresh {
            myFavorite.clear()
            showFavorite()
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        return rootView
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(Favorite.tb_favorite)
            val favorite = result.parseList(classParser<Favorite>())
            myFavorite.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}

