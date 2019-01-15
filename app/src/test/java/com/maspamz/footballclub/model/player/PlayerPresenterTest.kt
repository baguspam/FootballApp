package com.maspamz.footballclub.model.player

import com.google.gson.Gson
import com.maspamz.footballclub.TestContextProvider
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.api.ApiTheSportDB
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.config.data.LeagueResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Created by Maspamz on 20/09/2018.
 *
 */

class PlayerPresenterTest{

    @Mock
    private lateinit var view: PlayerView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var getData: Gson

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, getData, TestContextProvider())
    }

    @Test
    fun testGetPlayerList(){

        val player : MutableList<League> = mutableListOf()
        val response = LeagueResponse(teams = player, events = player, players = player, player = player, event = player)
        val league = "133604"

        //Testing response get data from server api
        `when`(getData.fromJson(apiRepository.doRequest(ApiTheSportDB.getPlayer(league)), LeagueResponse::class.java)).thenReturn(response)

        //Testing get data
        presenter.getPlayerList(league)

        verify(view).showLoading()
        verify(view).showPlayerList(player)
        verify(view).hideLoading()

    }
}