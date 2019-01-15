package com.maspamz.footballclub.model.detail

import com.google.gson.Gson
import com.maspamz.footballclub.TestContextProvider
import com.maspamz.footballclub.config.api.ApiRepository
import com.maspamz.footballclub.config.api.ApiTheSportDB
import com.maspamz.footballclub.config.data.League
import com.maspamz.footballclub.config.data.LeagueResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Maspamz on 20/09/2018.
 *
 */
class DetailPresenterTest{
    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var getData: Gson

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, getData, TestContextProvider())
    }

    @Test
    fun testGetDetailMatchList(){

        val club : MutableList<League> = mutableListOf()
        val response = LeagueResponse(teams = club, events = club, players = club, player = club, event = club)
        val league = "4328"

        //Testing response get data from server api
        Mockito.`when`(getData.fromJson(apiRepository.doRequest(ApiTheSportDB.getClub(league)), LeagueResponse::class.java)).thenReturn(response)

        //Testing get data
        presenter.getDetailMatchView(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showDetailMatchList(club)
        Mockito.verify(view).hideLoading()

    }
}