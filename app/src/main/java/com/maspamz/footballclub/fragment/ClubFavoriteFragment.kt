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
import com.maspamz.footballclub.DetailActivityTabs
import com.maspamz.footballclub.R
import com.maspamz.footballclub.adapter.ClubFavoriteAdapter
import com.maspamz.footballclub.config.db.database
import com.maspamz.footballclub.config.data.FavoriteClub
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by Maspamz on 12/09/2018.
 *
 */

class ClubFavoriteFragment : Fragment() {

    private var myFavoriteClub: MutableList<FavoriteClub> = mutableListOf()
    private lateinit var adapter: ClubFavoriteAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        val activity = activity
        swipeRefreshLayout = rootView.l_swipe_favorite
        //RecycleView plus Adapter
        adapter = ClubFavoriteAdapter(myFavoriteClub) {
            startActivity(intentFor<DetailActivityTabs>(
                    "ID_ITEM" to it.teamID
            ))
        }

        listTeam = rootView.lrv_view_favorite
        listTeam.layoutManager = LinearLayoutManager(activity)
        listTeam.adapter = adapter

        myFavoriteClub.clear()
        showFavoriteClub()

        swipeRefreshLayout.onRefresh {
            myFavoriteClub.clear()
            showFavoriteClub()
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        return rootView
    }

    private fun showFavoriteClub(){
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(FavoriteClub.tb_favorite_club)
            val favoriteClub = result.parseList(classParser<FavoriteClub>())
            myFavoriteClub.addAll(favoriteClub)
            adapter.notifyDataSetChanged()
        }
    }

}

