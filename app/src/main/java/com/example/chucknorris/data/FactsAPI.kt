package com.example.chucknorris.data

import com.example.chucknorris.data.models.Fact
import retrofit2.http.GET

interface FactsAPI {
    @GET("jokes/random")
    suspend fun getFact(): Fact
}