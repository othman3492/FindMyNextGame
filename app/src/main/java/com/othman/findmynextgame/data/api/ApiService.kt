package com.othman.findmynextgame.data.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {


    val apiClient: ApiClient = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/api/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)
}