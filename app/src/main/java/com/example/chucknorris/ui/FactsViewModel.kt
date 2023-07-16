package com.example.chucknorris.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.data.UserPreferences
import com.example.chucknorris.data.models.Fact
import com.example.chucknorris.data.FactsRepository
import kotlinx.coroutines.launch

class FactsViewModel: ViewModel() {

    private val repository = FactsRepository()

    private val _randomFactLiveData = MutableLiveData<Fact>()

    val randomFactLiveData: LiveData<Fact> = _randomFactLiveData

    fun generateFact() {
        viewModelScope.launch {
            val value = repository.getFact()
            _randomFactLiveData.postValue(value)
        }
    }
    fun getUserName(sharedPreferences: UserPreferences): String {
        return sharedPreferences.getName().toString()
    }

    fun saveUserName(name: String, sharedPreferences: UserPreferences){
        sharedPreferences.saveName(name)
    }
}
