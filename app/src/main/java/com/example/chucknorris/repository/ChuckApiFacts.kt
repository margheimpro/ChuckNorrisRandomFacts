package com.example.chucknorris.repository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChuckApiFacts {
    @GET("jokes/random")
    suspend fun getFact(): Facts
}