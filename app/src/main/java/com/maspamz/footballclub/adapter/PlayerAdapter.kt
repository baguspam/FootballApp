package com.maspamz.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.maspamz.footballclub.R
import com.maspamz.footballclub.config.data.League
import kotlinx.android.synthetic.main.item_player.view.*

/**
 * Created by Maspamz on 9/6/2018.
 *
 *
 */

class PlayerAdapter (private val myPlayer: List<League>, private val listener: (League) -> Unit)
    :RecyclerView.Adapter<PlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(myPlayer[position],listener)
    }

    override fun getItemCount(): Int = myPlayer.size




}
class PlayerViewHolder(private var view:View): RecyclerView.ViewHolder(view) {
    fun bindItem(myPlayer: League, listener: (League) -> Unit){
        view.tv_id_player.text = myPlayer.idPlayer
        view.tv_name_player.text = myPlayer.strPlayer
        if(myPlayer.strCutout != null){
            Glide.with(itemView.context).load(myPlayer.strCutout).into(view.img_player)
        }else{
            Glide.with(itemView.context).load(R.drawable.football_player_black).into(view.img_player)
        }

        itemView.setOnClickListener { listener(myPlayer) }
    }

}

