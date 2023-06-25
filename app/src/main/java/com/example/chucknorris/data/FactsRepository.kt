package com.example.chucknorris.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FactsRepository {

    private val factApi: FactsAPI

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        factApi = retrofit.create(FactsAPI::class.java)

    }

    suspend fun getFact() = factApi.getFact()

}