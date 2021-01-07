package com.othman.findmynextgame.data.repositories

import com.othman.findmynextgame.data.api.ApiClient
import com.othman.findmynextgame.model.Game
import com.othman.findmynextgame.model.Result
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class GameRepository @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun getGameList() = apiClient.getGameList()
}


