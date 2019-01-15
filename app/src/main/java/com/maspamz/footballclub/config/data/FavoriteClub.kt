package com.maspamz.footballclub.config.data

/**
 * Created by Maspamz on 13/09/2018.
 *
 */

data class FavoriteClub(
        val id: Long?,
        val teamID: String?,
        val teamName: String?,
        val teamBadge: String?,
        val teamDescription: String?) {

    companion object {
        const val tb_favorite_club: String = "tb_favorite_club"
        const val team_id: String = "team_id"
        const val team_name: String = "team_name"
        const val team_badge: String = "team_badge"
        const val team_description: String = "team_description"
    }
}