package com.maspamz.footballclub.config.api

import java.net.URL

/**
 * Created by PU on 9/6/2018.
 *
 */
class ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}