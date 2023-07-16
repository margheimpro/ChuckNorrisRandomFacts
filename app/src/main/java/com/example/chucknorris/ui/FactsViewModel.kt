package com.example.chucknorris.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.data.models.Fact
import com.example.chucknorris.data.FactsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FactsViewModel: ViewModel() {

    private val repository = FactsRepository()

    val randomFactResult = MutableSharedFlow<Fact>()

    fun generateFact() {
        viewModelScope.launch {
            val value = repository.getFact()
            randomFactResult.emit(value)
        }
    }
}