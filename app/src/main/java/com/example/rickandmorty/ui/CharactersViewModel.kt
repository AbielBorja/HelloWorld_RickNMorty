package com.example.rickandmorty.ui

import androidx.lifecycle.*
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
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

            } finally {
                isLoading = false
            }
        }
    }
}
