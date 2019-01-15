package com.maspamz.footballclub.config.data

/**
 * Created by Maspamz on 12/09/2018.
 *
 */

data class Favorite(
        val id: Long?,
        val idEvent: String?,
        val dateEvent: String?,
        val idClub1: String?,
        val nameClub1: String?,
        val scoreClub1: String?,
        val idClub2: String?,
        val nameClub2: String?,
        val scoreClub2: String?) {

    companion object {
        const val tb_favorite: String = "tb_favorite"
        const val id: String = "id"
        const val id_event: String = "id_event"
        const val date_event: String = "date_event"
        const val id_club1: String = "id_club1"
        const val name_club1: String = "name_club1"
        const val score_club1: String = "score_club1"
        const val id_club2: String = "id_club2"
        const val name_club2: String = "name_club2"
        const val score_club2: String = "score_club2"
    }
}