package com.maspamz.footballclub

import com.maspamz.footballclub.helper.CoroutinesContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Maspamz on 20/09/2018.
 *
 */
class TestContextProvider : CoroutinesContextProvider() {
    override val main: CoroutineContext = Unconfined
}