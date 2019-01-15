package com.maspamz.footballclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maspamz.footballclub.R
import com.maspamz.footballclub.config.data.Favorite
import kotlinx.android.synthetic.main.item_match_list.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent


/**
 * Created by Maspamz on 9/6/2018.
 *
 */

class MatchFavoriteAdapter (private val myFavorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    :RecyclerView.Adapter<MatchFavoriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchFavoriteViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_list, parent, false)
        return MatchFavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchFavoriteViewHolder, position: Int) {
        holder.bindItem(myFavorite[position],listener)
    }

    override fun getItemCount(): Int = myFavorite.size

}
class MatchFavoriteViewHolder(private var view:View): RecyclerView.ViewHolder(view) {

    fun bindItem(myFavorite: Favorite, listener: (Favorite) -> Unit) {
        view.tv_name_match1.text = myFavorite.nameClub1
        view.tv_name_match2.text = myFavorite.nameClub2

        //Convert Date format Indonesian
        var dateEvent = myFavorite.dateEvent
        val locale = Locale("ID")
        var spfMe = SimpleDateFormat("yyyy-M-d", locale)
        val newDate: Date
        try {
            newDate = spfMe.parse(dateEvent)
            spfMe = SimpleDateFormat("E, d MMM  yyyy", locale)
            dateEvent = spfMe.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        view.tv_date_match.text = dateEvent
        view.tv_id_event.text = myFavorite.idEvent
        if (myFavorite.scoreClub1 != null) {
            view.tv_score.text = (myFavorite.scoreClub1.toString() + " VS " + (myFavorite.scoreClub2))
        } else {

            view.tv_score.text = (" VS ")
            view.l_alarm.visibility = View.GONE
        }
        itemView.setOnClickListener { listener(myFavorite) }
    }


}

