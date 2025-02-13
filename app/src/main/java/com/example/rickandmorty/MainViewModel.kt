package com.example.rickandmorty

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.network.ApiClient
import com.example.rickandmorty.network.Character
import com.example.rickandmorty.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private val _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData: LiveData<ScreenState<List<Character>?>>
        get() = _charactersLiveData

    private val charactersList = mutableListOf<Character>()
    private var currentPage = 1
    private var isLoading = false
    var hasMorePages = true
    private var totalPages = 1

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        if (isLoading || !hasMorePages) return
        isLoading = true

        Log.d("PAGINACIÓN", "Cargando página: $currentPage de $totalPages")

        _charactersLiveData.postValue(ScreenState.Loading(charactersList))
        val client = repository.getCharacters(currentPage.toString())

        client.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                isLoading = false
                if (response.isSuccessful) {
                    response.body()?.let { characterResponse ->
                        charactersList.addAll(characterResponse.result)
                        _charactersLiveData.postValue(ScreenState.Success(charactersList))


                        if (currentPage == 1) {
                            totalPages = characterResponse.pageInfo.pages
                        }


                        if (characterResponse.pageInfo.next != null) {
                            currentPage++
                        } else {
                            hasMorePages = false
                            Log.d("PAGINACIÓN", "Se cargaron todos los personajes.")
                        }
                    }
                } else {
                    _charactersLiveData.postValue(ScreenState.Error(response.code().toString(), charactersList))
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                isLoading = false
                _charactersLiveData.postValue(ScreenState.Error(t.message.toString(), charactersList))
            }
        })
    }
}

