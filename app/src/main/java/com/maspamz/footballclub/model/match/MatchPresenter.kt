package com.maspamz.footballclub.model.match

import com.google.gson.Gson
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.api.ApiTheSportDB
import com.maspamz.footballclub.config.data.LeagueResponse
import com.maspamz.footballclub.helper.CoroutinesContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Maspamz on 19/09/2018.
 *
 */

class MatchPresenter(private val view: MatchView,
                         private val apiRepository: ApiRepository,
                         private val getData: Gson, private val context: CoroutinesContextProvider = CoroutinesContextProvider()){

    fun getMatchNext(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                getData.fromJson(apiRepository
                        .doRequest(ApiTheSportDB.getMatchNext(league)),
                        LeagueResponse::class.java
                )
            }
            view.hideLoading()
            view.showMatchList(data.await().events)
        }
    }

    fun getMatchLast(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                getData.fromJson(apiRepository
                        .doRequest(ApiTheSportDB.getMatchLast(league)),
                        LeagueResponse::class.java
                )
            }
            view.hideLoading()
            view.showMatchList(data.await().events)
        }
    }

    fun getMatchSearch(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                getData.fromJson(apiRepository
                        .doRequest(ApiTheSportDB.getSearchMatch(league)),
                        LeagueResponse::class.java
                )
            }
            view.hideLoading()
            view.showMatchList(data.await().event)
        }
    }
}
