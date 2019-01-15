package com.maspamz.footballclub.config.api

import com.maspamz.footballclub.BuildConfig

/**
 * Created by PU on 9/6/2018.
 *
 */
object ApiTheSportDB {

    //URL for Club
    fun getClub(league: String?): String {
        //return "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=Spain"
        val league1 = league?.replace(" ", "%20")
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/search_all_teams.php?l=" + league1
    }

    //URL for Match Next
    fun getMatchNext(league: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/eventsnextleague.php?id="+league
    }

    //URL for Match Last
    fun getMatchLast(league: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/eventspastleague.php?id=" +league
    }

    //URL for Detail Event
    fun getDetailMatch(league: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/lookupevent.php?id=" +league
    }

    //URL for Detail Club by id
    fun getDetailClub(league: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/lookupteam.php?id=" +league
    }

    //URL for Search Data
    fun getSearchClub(league: String?): String {
        // https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=Arsenal
        val league1 = league?.replace(" ", "%20")
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/searchteams.php?t=" +league1
    }

    //URL for Search Data
    fun getSearchMatch(league: String?): String {
        // https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=Arsenal_vs_Chelsea
        val league1 = league?.replace(" ", "_")
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/searchevents.php?e=" +league1
    }

    fun getPlayer(league: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=133604
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/lookup_all_players.php?id=" +league
    }

    fun getDetailPlayer(league: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id=34145937
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.API_KEY}" + "/lookupplayer.php?id=" +league
    }
}