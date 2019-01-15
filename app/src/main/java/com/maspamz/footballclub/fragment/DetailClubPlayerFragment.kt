package com.maspamz.footballclub.fragment

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.maspamz.footballclub.DetailActivityPlayer

import com.maspamz.footballclub.R
import com.maspamz.footballclub.adapter.PlayerAdapter
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.data.FavoriteClub
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.config.db.database
import com.maspamz.footballclub.model.player.PlayerPresenter
import com.maspamz.footballclub.model.player.PlayerView
import kotlinx.android.synthetic.main.fragment_player.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by Maspamz on 07/09/2018.
 *
 */

class DetailClubPlayerFragment : Fragment(), PlayerView {

    private var name: String = ""
    private var desc: String = ""
    private var img: String = ""
    private var teamID: String = ""
    private var player: MutableList<League> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_player, container, false)
        val activity = activity

        val intent = activity!!.intent
        teamID = intent!!.getStringExtra("ID_ITEM")
        Log.d("Data ID PLAYER", teamID)

        //RecycleView plus Adapter
        adapter = PlayerAdapter(player){
            startActivity(intentFor<DetailActivityPlayer>(
                    "ID_PLAYER"  to it.idPlayer
            ))
        }

        listTeam = rootView.lrv_view_player
        listTeam.layoutManager = LinearLayoutManager(activity)
        listTeam.adapter = adapter


        swipeRefreshLayout = rootView.l_swipe
        progressBar = rootView.l_progressBar

        swipeRefreshLayout.onRefresh {
            presenter.getPlayerList(teamID)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        //Get data
        val request = ApiRepository()
        val getData = Gson()
        presenter = PlayerPresenter(this, request, getData)

        //Request Get data for api with one ID
        presenter.getPlayerList(teamID)
        presenter.getClubList2(teamID)

        //checkFavorite()
        return rootView
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showPlayerList(data: List<League>) {
        swipeRefreshLayout.isRefreshing = false
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showClubList2(data: List<League>) {
        name = data.get(index = 0).teamName.toString()
        img = data.get(index = 0).teamBadge.toString()
        desc = data.get(index = 0).teamDescription.toString()
    }

}// Required empty public constructor
