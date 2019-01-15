package com.maspamz.footballclub.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.maspamz.footballclub.DetailActivityMatch
import com.maspamz.footballclub.R
import com.maspamz.footballclub.adapter.MatchAdapter
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.model.match.MatchPresenter
import com.maspamz.footballclub.model.match.MatchView
import kotlinx.android.synthetic.main.fragment_match.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by Maspamz on 07/09/2018.
 *
 */

class MatchLastFragment : Fragment(), MatchView {

    private var events: MutableList<League> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var leagueName: String
    private lateinit var listTeam: RecyclerView
    private lateinit var listTeam2: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)
        val activity = activity
        //RecycleView plus Adapter
        adapter = MatchAdapter(events) {
            startActivity(intentFor<DetailActivityMatch>(
                    "ID_CLUB1" to it.idHomeTeam,
                    "ID_CLUB2" to it.idAwayTeam,
                    "ID_EVENT" to it.idEvent
            ))
        }

        listTeam = rootView.lrv_view_match
        listTeam.layoutManager = LinearLayoutManager(activity as Context?)
        listTeam.adapter = adapter

        linearLayout = rootView.l_search_match
        listTeam2 = rootView.lrv_view_match
        spinner = rootView.l_spinner_match
        swipeRefreshLayout = rootView.l_swipe
        progressBar = rootView.l_progressBar

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerItemsId = resources.getStringArray(R.array.league_id)
        spinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val getLeague = spinner.selectedItemPosition
                leagueName = spinnerItemsId[getLeague].toString()
                presenter.getMatchLast(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefreshLayout.onRefresh {
            presenter.getMatchLast(leagueName)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        //Get data
        val request = ApiRepository()
        val getData = Gson()
        presenter = MatchPresenter(this, request, getData)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater : MenuInflater) {
        menuInflater.inflate(R.menu.search_view, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.l_search_view)
        val search = menuItem.actionView as SearchView
        searching(search)
        super.onPrepareOptionsMenu(menu)
    }

    private fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 3) {
                    presenter.getMatchSearch(newText)
                    linearLayout.visibility = View.GONE

                }else{
                    presenter.getMatchLast(leagueName)
                    linearLayout.visibility = View.VISIBLE
                }
                //RecycleView plus Adapter
                adapter = MatchAdapter(events){
                    startActivity(intentFor<DetailActivityMatch>(
                            "ID_CLUB1" to it.idHomeTeam,
                            "ID_CLUB2" to it.idAwayTeam,
                            "ID_EVENT" to it.idEvent
                    ))

                }
                listTeam2.layoutManager = LinearLayoutManager(activity)
                listTeam2.adapter = adapter

                return true
            }
        })
    }



    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatchList(data: List<League>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}

