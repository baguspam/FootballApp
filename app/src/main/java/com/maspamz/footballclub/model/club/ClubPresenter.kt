package com.maspamz.footballclub.model.club

import com.google.gson.Gson
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.api.ApiTheSportDB
import com.maspamz.footballclub.config.data.LeagueResponse
import com.maspamz.footballclub.helper.CoroutinesContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Maspamz on 07/09/2018.
 *
 */

class ClubPresenter(private val view: ClubView,
                    private val apiRepository: ApiRepository,
                    private val getData: Gson, private val context: CoroutinesContextProvider = CoroutinesContextProvider()){

    fun getClubList(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                getData.fromJson(apiRepository
                        .doRequest(ApiTheSportDB.getClub(league)),
                        LeagueResponse::class.java
                )
            }

            view.hideLoading()
            view.showClubList(data.await().teams)

        }
    }

    fun getSearchList(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                getData.fromJson(apiRepository
                        .doRequest(ApiTheSportDB.getSearchClub(league)),
                        LeagueResponse::class.java
                )
            }

            view.hideLoading()
            view.showClubList(data.await().teams)

        }
    }
}