package com.maspamz.footballclub.model.detail

import com.maspamz.footballclub.config.data.League

/**
 * Created by Maspamz on 07/09/2018.
 *
 */

interface DetailView {

    fun showLoading()

    fun showDetailMatchList(data: List<League>)

    fun showDetailClub(data1: List<League>, data2: List<League>)

    fun showDetailPlayer(data: List<League>)

    fun hideLoading()

}