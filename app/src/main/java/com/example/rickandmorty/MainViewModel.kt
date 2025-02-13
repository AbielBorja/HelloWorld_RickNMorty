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

class MainViewModel(private val repository: Repository
    = Repository(ApiClient.apiService)): ViewModel() {

        private var _charactersLiveData = MutableLiveData<List<Character>>()
        val characterLiveData:LiveData<List<Character>>
            get() = characterLiveData


    init {
        fetchCharacter()
    }
    private fun fetchCharacter(){
        val client = repository.getCharacters("1")
        client.enqueue(object : Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if(response.isSuccessful){
                    // _charactersLiveData.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.d("Failure",t.message.toString())
            }
        })

    }

}