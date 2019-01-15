package com.maspamz.footballclub.config.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Maspamz on 07/09/2018.
 *
 */
data class League(

        //Pojo dari Club//
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null,

        //Pojo dari Event Next dan Last//
        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strTime")
        var strTime: String? = null,

        @SerializedName("strLeague")
        var strLeague: String? = null,

        //Club Home
        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = null,

        @SerializedName("intHomeScore")
        var intHomeScore: String? = null,

        @SerializedName("intHomeShots")
        var intHomeShots: String? = null,

        @SerializedName("strHomeFormation")
        var strHomeFormation: String? = null,

        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = null,

        //Club Away
        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null,

        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = null,

        @SerializedName("intAwayScore")
        var intAwayScore: String? = null,

        @SerializedName("intAwayShots")
        var intAwayShots: String? = null,

        @SerializedName("strAwayFormation")
        var strAwayFormation: String? = null,

        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = null,

        // Player
        @SerializedName("idPlayer")
        var idPlayer: String? = null,

        @SerializedName("strPlayer")
        var strPlayer: String? = null,

        @SerializedName("strNationality")
        var strNationality: String? = null,

        @SerializedName("dateBorn")
        var dateBorn: String? = null,

        @SerializedName("strBirthLocation")
        var strBirthLocation: String? = null,

        @SerializedName("strPosition")
        var strPosition: String? = null,

        @SerializedName("strHeight")
        var strHeight: String? = null,

        @SerializedName("strWeight")
        var strWeight: String? = null,

        @SerializedName("strCutout")
        var strCutout: String? = null,

        @SerializedName("strFanart1")
        var strFanart1: String? = null


)