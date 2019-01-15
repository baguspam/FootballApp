package com.maspamz.footballclub.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.maspamz.footballclub.R
import android.view.*
import android.widget.ProgressBar
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.model.detail.DetailPresenter
import com.maspamz.footballclub.model.detail.DetailView
import kotlinx.android.synthetic.main.fragment_detail_club_desc.*
import kotlinx.android.synthetic.main.fragment_detail_club_desc.view.*

/**
 * Created by Maspamz on 17/09/2018.
 *
 */

class DetailClubDescFragment : Fragment(), DetailView {


    private var name: String = ""
    private var desc: String = ""
    private var img: String = ""
    private var teamID: String = ""
    private lateinit var scrollView: ScrollView
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail_club_desc, container, false)
        val myActivity = activity
        //TODO 2 for support menu like favorite in action bar
        (myActivity as AppCompatActivity).supportActionBar?.title = "Detail Club"
        myActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = rootView.l_progressBar_clubDesc
        scrollView = rootView.l_scroll_clubDesc


        val intent = activity!!.intent
        teamID = intent!!.getStringExtra("ID_ITEM")

        //Get data
        val request = ApiRepository()
        val getData = Gson()
        presenter = DetailPresenter(this, request, getData)


        //Request Get data for api with one ID
        presenter.getDetailClubView(teamID, teamID)

        return rootView
    }

    override fun showDetailMatchList(data: List<League>) {
        //null because not get data from events
    }

    //TODO 3 Get data from api and show hide loading
    override fun showDetailClub(data1: List<League>, data2: List<League>) {
        name = data1.get(index = 0).teamName.toString()
        img = data1.get(index = 0).teamBadge.toString()
        desc = data1.get(index = 0).teamDescription.toString()

        Glide.with(this).load(img).into(img_clubDesc)
        tv_name_clubDesc.text = name
        tv_description_clubDes.text = desc
    }

    override fun showDetailPlayer(data: List<League>) {
        //
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

}