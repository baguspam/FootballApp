package com.maspamz.footballclub.model.player

import com.maspamz.footballclub.config.data.League

/**
 * Created by Maspamz on 07/09/2018.
 *
 */

interface PlayerView {

    fun showLoading()

    fun showPlayerList(data: List<League>)

    fun showClubList2(data: List<League>)

    fun hideLoading()

}