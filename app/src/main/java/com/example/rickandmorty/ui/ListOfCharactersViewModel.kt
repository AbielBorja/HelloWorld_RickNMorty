package com.example.rickandmorty.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.CharacterResponse
import com.example.rickandmorty.network.RickAndMortyBaseApiClient
import com.example.rickandmorty.repository.RickAndMortyRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListOfCharactersViewModel(private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository(RickAndMortyBaseApiClient.rickAndMortyCharactersApiService)) :
    ViewModel() {
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
        Log.d("PAGINATION", "LOADING PAGE: $currentPage")
        viewModelScope.launch {
            runCatching {
                rickAndMortyRepository.getCharacters(currentPage.toString())
                    .enqueue(object : Callback<CharacterResponse> {
                        override fun onResponse(
                            call: Call<CharacterResponse>,
                            response: Response<CharacterResponse>
                        ) {

                            isLoading = false
                            response.body()?.let { characterResponse ->
                                val newCharacters = characterResponse.result
                                accumulatedCharacters.addAll(newCharacters)
                                _pageCharactersLiveData.postValue(newCharacters)
                                hasMorePages = characterResponse.pageInfo.next != null
                                if (hasMorePages) currentPage++
                            }
                        }

                        override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                            isLoading = false
                        }
                    })
            }.onSuccess {

            }.onFailure {

            }
        }

    }
}