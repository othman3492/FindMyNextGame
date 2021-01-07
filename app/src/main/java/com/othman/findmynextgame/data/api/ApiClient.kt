package com.othman.findmynextgame.data.api

import com.othman.findmynextgame.model.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface ApiClient {


    @GET("games?key=1f3a4fe57165463284f839ed07b08abf")
    suspend fun getGameList(): Response<Result>

}