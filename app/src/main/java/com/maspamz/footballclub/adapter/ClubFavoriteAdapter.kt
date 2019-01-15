package com.maspamz.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.maspamz.footballclub.R
import com.maspamz.footballclub.config.data.FavoriteClub
import kotlinx.android.synthetic.main.item_club.view.*

/**
 * Created by Maspamz on 9/6/2018.
 *
 */

class ClubFavoriteAdapter (private val myFavorite: List<FavoriteClub>, private val listener: (FavoriteClub) -> Unit)
    :RecyclerView.Adapter<ClubFavoriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubFavoriteViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_club, parent, false)
        return ClubFavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubFavoriteViewHolder, position: Int) {
        holder.bindItem(myFavorite[position],listener)
    }

    override fun getItemCount(): Int = myFavorite.size




}
class ClubFavoriteViewHolder(private var view:View): RecyclerView.ViewHolder(view) {
    fun bindItem(myFavorite: FavoriteClub, listener: (FavoriteClub) -> Unit) {
        view.tv_name_club.text = myFavorite.teamName
        view.tv_description.text = myFavorite.teamDescription
        Glide.with(itemView.context).load(myFavorite.teamBadge).into(view.img_club)
        itemView.setOnClickListener { listener(myFavorite) }
    }


}

