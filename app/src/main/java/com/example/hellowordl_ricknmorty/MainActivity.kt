package com.example.hellowordl_ricknmorty

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hellowordl_ricknmorty.network.ApiClient
import com.example.hellowordl_ricknmorty.network.CharacterResponse
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient.apiService.fetchCharacters("1")

        client.enqueue(object : retrofit2.Callback<CharacterResponse>{


            override fun onResponse(
                call: retrofit2.Call<CharacterResponse>,
                response: retrofit2.Response<CharacterResponse>
            ){
                if(response.isSuccessful){
                    Log.d("Characters ", ""+response.body())
                    val result = response.body()?.result
                    result?.let{
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.characterRv)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<CharacterResponse>, t:Throwable){
                Log.e("Failed ", ""+t.message)
            }
        })
    }
}