package com.maspamz.footballclub

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.maspamz.footballclub.adapter.ClubAdapter
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.model.club.ClubPresenter
import com.maspamz.footballclub.model.club.ClubView
import kotlinx.android.synthetic.main.activity_main_club.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by Maspamz on 19/09/2018.
 *
 */

class MainClub : AppCompatActivity(), ClubView {

    private var teams: MutableList<League> = mutableListOf()
    private lateinit var presenter: ClubPresenter
    private lateinit var adapter: ClubAdapter
    private lateinit var leagueName: String
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_club)
        supportActionBar?.title = "Football Club"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorBitterSweet)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBitterSweetDark)
        }

        //RecycleView plus Adapter
        adapter = ClubAdapter(teams){
            startActivity(intentFor<DetailActivityTabs>(
                    "ID_ITEM"  to it.teamId
            ))
        }

        listTeam = lrv_view_club
        listTeam.layoutManager = LinearLayoutManager(this)
        listTeam.adapter = adapter


        spinner = l_spinner
        swipeRefreshLayout = l_swipe
        progressBar = l_progressBar

        val spinnerItems = resources.getStringArray(R.array.league)
        spinner.adapter =  ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getClubList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefreshLayout.onRefresh {
            presenter.getClubList(leagueName)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)

        //Get data
        val request = ApiRepository()
        val getData = Gson()
        presenter = ClubPresenter(this, request, getData)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater?.inflate(R.menu.search_view, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.findItem(R.id.l_search_view)
        val search = menuItem.actionView as SearchView
        searching(search)
        super.onPrepareOptionsMenu(menu)
        return true
    }

    private fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 3) {
                    presenter.getSearchList(newText)

                }else{
                    l_search_club.visibility = View.VISIBLE
                    presenter.getClubList(leagueName)
                }
                //RecycleView plus Adapter
                adapter = ClubAdapter(teams){
                    startActivity(intentFor<DetailActivityTabs>(
                            "ID_ITEM"  to it.teamId
                    ))
                }

                listTeam = lrv_view_club
                listTeam.layoutManager = LinearLayoutManager(applicationContext)
                listTeam.adapter = adapter
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

    override fun showClubList(data: List<League>) {
        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}