package com.maspamz.footballclub.adapter

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maspamz.footballclub.R
import com.maspamz.footballclub.config.data.League
import kotlinx.android.synthetic.main.item_match_list.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Maspamz on 9/6/2018.
 *
 */

class MatchAdapter (private val myMatchNext:List<League>, private val listener: (League) -> Unit)
    :RecyclerView.Adapter<MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_list, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(myMatchNext[position],listener)
    }

    override fun getItemCount(): Int = myMatchNext.size

}
class MatchViewHolder(private var view:View): RecyclerView.ViewHolder(view) {

    fun bindItem(myMatchNext: League, listener: (League) -> Unit){
        view.tv_name_match1.text = myMatchNext.strHomeTeam
        view.tv_name_match2.text = myMatchNext.strAwayTeam

        //Convert Date format Indonesian
        var dateEventMe = myMatchNext.dateEvent
        val locale = Locale("ID")
        var spfMe = SimpleDateFormat("yyyy-M-d", locale)
        val newDate: Date
        try {
            newDate = spfMe.parse(dateEventMe)
            spfMe = SimpleDateFormat("E, d MMM yyyy", locale)
            dateEventMe = spfMe.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        view.tv_date_match.text = dateEventMe
        view.tv_time_match.text = myMatchNext.strTime?.substring(0,5)
        view.tv_id_event.text = myMatchNext.idEvent
        if (myMatchNext.intHomeScore != null) {
           view.tv_score.text = (myMatchNext.intHomeScore + " VS " + myMatchNext.intAwayScore)
        } else {
            view.tv_score.text = (" VS ")
            view.l_alarm.visibility = View.GONE
        }
        itemView.setOnClickListener { listener(myMatchNext) }


    }



}



