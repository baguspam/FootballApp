package com.maspamz.footballclub.model.player

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

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val getData: Gson, private val context: CoroutinesContextProvider = CoroutinesContextProvider()){

    fun getPlayerList(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                getData.fromJson(apiRepository
                        .doRequest(ApiTheSportDB.getPlayer(league)),
                        LeagueResponse::class.java
                )
            }

            view.hideLoading()
            view.showPlayerList(data.await().player)

        }
    }

    fun getClubList2(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                getData.fromJson(apiRepository
                        .doRequest(ApiTheSportDB.getClub(league)),
                        LeagueResponse::class.java
                )
            }

            view.hideLoading()
            view.showClubList2(data.await().teams)

        }
    }
}