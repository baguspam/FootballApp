package com.maspamz.footballclub

import android.database.sqlite.SQLiteConstraintException
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.google.gson.Gson
import com.maspamz.footballclub.adapter.pager.DetailClubPager
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.data.FavoriteClub
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.config.db.database
import com.maspamz.footballclub.model.detail.DetailPresenter
import com.maspamz.footballclub.model.detail.DetailView
import kotlinx.android.synthetic.main.activity_tab_detail_club.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

/**
 * Created by Maspamz on 17/09/2018.
 *
 */

class DetailActivityTabs : AppCompatActivity(), DetailView {

    private var name: String = ""
    private var desc: String = ""
    private var img: String = ""
    private var teamID: String = ""
    private lateinit var linearLayout: LinearLayout

    private lateinit var presenter: DetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_detail_club)
        //TODO 2 for support menu like favorite in action bar
        supportActionBar?.title = "Detail Club"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorBitterSweet)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBitterSweetDark)
        }

        val intent = intent
        teamID = intent.getStringExtra("ID_ITEM")
        Log.d("Data ID CLUB", teamID)

        linearLayout = l_layout_tab

        //Get data
        val request = ApiRepository()
        val getData = Gson()
        presenter = DetailPresenter(this, request, getData)


        //Request Get data for api with one ID
        presenter.getDetailClubView(teamID, teamID)

        //Check id favorite, What id in detail, like such as id from db ?
        checkFavorite()

        configureTabLayout()
    }

    private fun configureTabLayout() {

        l_tabs_main.addTab(l_tabs_main.newTab().setText("Description"))
        l_tabs_main.addTab(l_tabs_main.newTab().setText("Club Player"))

        val adapter = DetailClubPager(supportFragmentManager,
                l_tabs_main.tabCount)
        l_viewpager.adapter = adapter

        l_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(l_tabs_main))
        l_tabs_main.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                l_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    override fun showLoading() {
        //null
    }

    override fun showDetailMatchList(data: List<League>) {
        //
    }

    override fun showDetailClub(data1: List<League>, data2: List<League>) {
        name = data1.get(index = 0).teamName.toString()
        img = data1.get(index = 0).teamBadge.toString()
        desc = data1.get(index = 0).teamDescription.toString()

    }

    override fun showDetailPlayer(data: List<League>) {
        //null
    }

    override fun hideLoading() {
        //null
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    //TODO 4 Make menu and function addFavorite, removeFavorite, and checkFavorite
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) {
                    removeFavorite()
                } else{
                    addFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun addFavorite(){
        try {
            database.use {
                insert(FavoriteClub.tb_favorite_club,
                        FavoriteClub.team_id to teamID,
                        FavoriteClub.team_name to name,
                        FavoriteClub.team_badge to img,
                        FavoriteClub.team_description to desc
                )
            }
            snackbar(linearLayout, R.string.add_favorite).show()
        }catch (e: SQLiteConstraintException){
            snackbar(linearLayout, e.localizedMessage).show()
        }
    }

    private fun removeFavorite(){
        try {
            database.use {
                delete(FavoriteClub.tb_favorite_club, "(team_id = {team_id})",
                        "team_id" to teamID)
            }
            snackbar(linearLayout, R.string.remove_favorite).show()
        } catch (e: SQLiteConstraintException){
            snackbar(linearLayout, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
    }

    private fun checkFavorite() {
        database.use {
            val result = select(FavoriteClub.tb_favorite_club)
                    .whereArgs("(team_id = {team_id})", "team_id" to teamID)
            val favorite = result.parseList(classParser<FavoriteClub>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
