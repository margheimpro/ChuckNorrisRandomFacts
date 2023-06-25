package com.example.chucknorris.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.data.models.Fact
import com.example.chucknorris.data.FactsRepository
import kotlinx.coroutines.launch

class FactsViewModel: ViewModel() {

    private val repository = FactsRepository()

    private val _Random_factLiveData = MutableLiveData<Fact>()

    val factLiveData: LiveData<Fact> = _Random_factLiveData

    fun generateFact() {
        viewModelScope.launch {
            val value = repository.getFact()
            _Random_factLiveData.postValue(value)
        }
    }
}