package com.maspamz.footballclub

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.model.detail.DetailPresenter
import com.maspamz.footballclub.model.detail.DetailView
import kotlinx.android.synthetic.main.activity_detail_player.*
/**
 * Created by Maspamz on 9/7/2018.
 *
 */

class DetailActivityPlayer : AppCompatActivity(), DetailView {

    //TODO 1 Declaration for variable, view access in all private in here, presenter, and menu favorite in action bar
    private var playerID: String = ""
    private lateinit var scrollView: ScrollView
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: DetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        //TODO 2 for support menu like favorite in action bar
        supportActionBar?.title = "Detail Player"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorBitterSweet)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBitterSweetDark)
        }

        progressBar = l_progressBar
        scrollView = l_scroll


        val intent = intent
        playerID = intent.getStringExtra("ID_PLAYER")
        Log.d("Data ID PLAYER", playerID)

        //Get data
        val request = ApiRepository()
        val getData = Gson()
        presenter = DetailPresenter(this, request, getData)


        //Request Get data for api with one ID
        presenter.getDetailPlayerView(playerID)

    }


    override fun showDetailMatchList(data: List<League>) {
        //null because not get data from events
    }

    override fun showDetailClub(data1: List<League>, data2: List<League>) {
        //null because not get data from events
    }

    //TODO 3 Get data from api and show hide loading
    override fun showDetailPlayer(data: List<League>) {
        val name = data.get(index = 0).strPlayer.toString()
        val img = data.get(index = 0).strFanart1.toString()
        val desc = data.get(index = 0).teamDescription.toString()
        val dateBorn = data.get(index = 0).dateBorn.toString()
        val placeBorn = data.get(index = 0).strBirthLocation.toString()
        val heightTxt = data.get(index = 0).strHeight.toString()
        val weightTxt = data.get(index = 0).strWeight.toString()
        val positionTxt = data.get(index = 0).strPosition.toString()

        Glide.with(this).load(img).into(img_player)
        tv_name_player.text = name
        tv_description_player.text = desc
        tv_date_born.text = dateBorn
        tv_place_born.text = placeBorn
        tv_height.text = heightTxt
        tv_weight.text = weightTxt
        tv_position.text = positionTxt
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuItem = menu
        return true
    }

    //TODO 4 Make menu and function addFavorite, removeFavorite, and checkFavorite
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}