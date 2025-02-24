package com.example.rickandmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.network.RickAndMortyBaseApiClient
import com.example.rickandmorty.repository.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: RickAndMortyRepository = RickAndMortyRepository(
        RickAndMortyBaseApiClient.rickAndMortyCharactersApiService
    )
) : ViewModel() {

    private val _pageCharactersLiveData = MutableLiveData<List<Character>>()
    val pageCharactersLiveData: LiveData<List<Character>> get() = _pageCharactersLiveData

    private val accumulatedCharacters = mutableListOf<Character>()
    private var currentPage = 1
    private var isLoading = false
    var hasMorePages = true
        private set

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        if (isLoading || !hasMorePages) return
        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCharacters(currentPage.toString())
                val newCharacters = response.result
                accumulatedCharacters.addAll(newCharacters)
                _pageCharactersLiveData.postValue(newCharacters)
                hasMorePages = response.pageInfo.next != null
                if (hasMorePages) {
                    currentPage++
                }
            } catch (e: Exception) {
                // Manejo del error
            } finally {
                isLoading = false
            }
        }
    }
}
