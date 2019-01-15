package com.maspamz.footballclub

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.data.League
import kotlinx.android.synthetic.main.activity_detail_match.*
import android.view.View
import android.widget.ScrollView
import com.maspamz.footballclub.R.drawable.*
import com.maspamz.footballclub.R.id.add_to_favorite
import com.maspamz.footballclub.R.menu.detail_menu
import com.maspamz.footballclub.config.db.database
import com.maspamz.footballclub.config.data.Favorite
import com.maspamz.footballclub.model.detail.DetailPresenter
import com.maspamz.footballclub.model.detail.DetailView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Maspamz on 10/09/2018.
 *
 */

class DetailActivityMatch: AppCompatActivity(), DetailView {

    //TODO 1 Declaration for variable, view access in all private in here, presenter, and menu favorite in action bar
    private lateinit var idClub1: String
    private lateinit var idClub2: String
    private lateinit var idEvent: String
    private lateinit var dateEvent: String
    private lateinit var nameClub1: String
    private lateinit var nameClub2: String
    private lateinit var scoreClub1: String
    private lateinit var scoreClub2: String

    private lateinit var scrollView: ScrollView
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: DetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        
        //TODO 2 for support menu like favorite in action bar
        supportActionBar?.title = "Detail Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idClub1 = intent.getStringExtra("ID_CLUB1")
        idClub2 = intent.getStringExtra("ID_CLUB2")
        idEvent = intent.getStringExtra("ID_EVENT")
        id_match.text = idEvent
        Log.d("Check id Event", idEvent)
        progressBar = l_progressBar
        scrollView = l_scroll

        checkFavorite()
        //Get data from Api
        val request = ApiRepository()
        val getData = Gson()
        presenter = DetailPresenter(this, request, getData)
        presenter.getDetailMatchView(idEvent)
        presenter.getDetailClubView(idClub1, idClub2)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
           /* android.R.id.home -> {
                finish()
                true
            }*/
            add_to_favorite -> {
                    if (isFavorite) removeFavorite() else addFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    

    //TODO 3 Get data from api and show hide loading
    override fun showDetailMatchList(data: List<League>) {

        dateEvent = data.get(index = 0).dateEvent.toString()

        //Convert Date to format Indonesian
        val locale = Locale("ID")
        var spfMe = SimpleDateFormat("yyyy-M-d", locale)
        val newDate: Date
        try {
            newDate = spfMe.parse(dateEvent)
            spfMe = SimpleDateFormat("E, d MMM yyyy", locale)
            dateEvent = spfMe.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        //End Convert Date format Indonesian

        if (data.get(index = 0).intHomeScore != null) {
            //Set to textView
            l_match_date.text = dateEvent
            l_match_time.text = data.get(index = 0).strTime?.substring(0,5)

            //Club Home
            l_score1.text = data.get(index = 0).intHomeScore
            l_formation_club1.text = data.get(index = 0).strHomeFormation
            l_shots1.text = data.get(index = 0).intHomeShots
            l_goalkeeper1.text = data.get(index = 0).strHomeLineupGoalkeeper
            l_name_goals1.text = data.get(index = 0).strHomeGoalDetails
            l_midfield1.text = data.get(index = 0).strHomeLineupMidfield
            l_defense1.text = data.get(index = 0).strHomeLineupDefense
            l_forward1.text = data.get(index = 0).strHomeLineupForward
            l_substitutes1.text = data.get(index = 0).strHomeLineupSubstitutes

            //Club Away
            l_score2.text = data.get(index = 0).intAwayScore
            l_formation_club2.text = data.get(index = 0).strAwayFormation
            l_shots2.text = data.get(index = 0).intAwayShots
            l_goalkeeper2.text = data.get(index = 0).strAwayLineupGoalkeeper
            l_name_goals2.text = data.get(index = 0).strAwayGoalDetails
            l_midfield2.text = data.get(index = 0).strAwayLineupMidfield
            l_defense2.text = data.get(index = 0).strAwayLineupDefense
            l_forward2.text = data.get(index = 0).strAwayLineupForward
            l_substitutes2.text = data.get(index = 0).strAwayLineupSubstitutes

            //Get data dari JSON ke Variable karena events: League error
            nameClub1 = data.get(index = 0).strHomeTeam.toString()
            nameClub2 = data.get(index = 0).strAwayTeam.toString()
            scoreClub1 = data.get(index = 0).intHomeScore.toString()
            scoreClub2 = data.get(index = 0).intAwayScore.toString()
            //End Get

        }else{
            //Set to textView
            l_match_date.text = dateEvent
            l_match_time.text = data.get(index = 0).strTime?.substring(0,5)
            //Club Home
            l_shots1.text = "-"
            l_goalkeeper1.text = "-"
            l_name_goals1.text = "-"
            l_midfield1.text = "-"
            l_defense1.text = "-"
            l_forward1.text = "-"
            l_substitutes1.text = "-"

            //Club Away
            l_shots2.text = "-"
            l_goalkeeper2.text = "-"
            l_name_goals2.text = "-"
            l_midfield2.text = "-"
            l_defense2.text = "-"
            l_forward2.text = "-"
            l_substitutes2.text = "-"

            //Get data dari JSON ke Variable karena events: League error
            nameClub1 = data.get(index = 0).strHomeTeam.toString()
            nameClub2 = data.get(index = 0).strAwayTeam.toString()
            scoreClub1 = ""
            scoreClub2 = ""
            //End Get

        }

    }

    override fun showDetailClub(data1: List<League>, data2: List<League>) {

        l_name_club1.text = data1.get(index = 0).teamName
        l_name_club2.text = data2.get(index = 0).teamName
        Glide.with(this).load(data1.get(index = 0).teamBadge).into(l_img_club1)
        Glide.with(this).load(data2.get(index = 0).teamBadge).into(l_img_club2)
    }

    override fun showDetailPlayer(data: List<League>) {
        //null
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    //TODO 4 Make menu and function addFavorite, removeFavorite, and checkFavorite
    private fun addFavorite(){
        try {
            database.use {
                insert(Favorite.tb_favorite,
                        Favorite.id_event to idEvent,
                        Favorite.date_event to dateEvent,
                        Favorite.id_club1 to idClub1,
                        Favorite.name_club1 to nameClub1,
                        Favorite.score_club1 to scoreClub1,
                        Favorite.id_club2 to idClub2,
                        Favorite.name_club2 to nameClub2,
                        Favorite.score_club2 to scoreClub2
                        )
            }
            snackbar(l_layout_detail, R.string.add_favorite).show()
        }catch (e: SQLiteConstraintException){
            snackbar(l_layout_detail, e.localizedMessage).show()
        }
    }

    private fun removeFavorite(){
        try {
            database.use {
                delete(Favorite.tb_favorite, "(id_event = {id_event})",
                        "id_event" to idEvent)
            }
            snackbar(l_layout_detail, R.string.remove_favorite).show()
        } catch (e: SQLiteConstraintException){
            snackbar(l_layout_detail, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite_white)
    }

    private fun checkFavorite() {
        database.use {
            val result = select(Favorite.tb_favorite)
                    .whereArgs("(id_event = {id_event})", "id_event" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
