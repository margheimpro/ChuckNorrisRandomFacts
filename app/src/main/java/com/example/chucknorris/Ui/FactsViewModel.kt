package com.example.chucknorris.Ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.repository.ChuckApiFacts
import com.example.chucknorris.repository.Facts
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FactsViewModel: ViewModel() {

    private val factApi: ChuckApiFacts

    private val _factsLiveData = MutableLiveData<Facts>()

    val factsLiveData: LiveData<Facts> = _factsLiveData

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        factApi = retrofit.create(ChuckApiFacts::class.java)
    }

    fun generateFact() {
        viewModelScope.launch {
            val value = factApi.getFact()
            _factsLiveData.postValue(value)
        }
    }
}