package com.maspamz.footballclub.model.match

import com.maspamz.footballclub.config.data.League

/**
 * Created by Maspamz on 07/09/2018.
 *
 */

interface MatchView {

    fun showLoading()

    //fun showMatchNextList(dataHome: List<League>, dataAway: List<League>)

    fun showMatchList(data: List<League>)

    fun hideLoading()

}