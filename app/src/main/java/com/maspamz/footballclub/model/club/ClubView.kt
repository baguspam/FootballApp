package com.maspamz.footballclub.model.club

import com.maspamz.footballclub.config.data.League

/**
 * Created by Maspamz on 07/09/2018.
 *
 */

interface ClubView {

    fun showLoading()

    fun showClubList(data: List<League>)

    fun hideLoading()

}