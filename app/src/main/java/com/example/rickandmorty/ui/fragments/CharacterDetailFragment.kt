package com.example.rickandmorty.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.Episode
import com.example.rickandmorty.network.RickAndMortyBaseApiClient
import com.example.rickandmorty.ui.Adapters.EpisodeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailFragment : Fragment() {

    private lateinit var character: Character
    private lateinit var rvEpisodes: RecyclerView
    private lateinit var episodeAdapter: EpisodeAdapter
    private val episodesList: MutableList<Episode> = mutableListOf()

    companion object {
        fun newInstance(character: Character?) = CharacterDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("character", character)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments?.getParcelable("character")
            ?: throw IllegalArgumentException("Character data is required")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageViewCharacter = view.findViewById<ImageView>(R.id.imageViewCharacter)
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewStatus = view.findViewById<TextView>(R.id.textViewStatus)
        val textViewSpecies = view.findViewById<TextView>(R.id.textViewSpecies)
        val textViewGender = view.findViewById<TextView>(R.id.textViewGender)
        val textViewOrigin = view.findViewById<TextView>(R.id.textViewOrigin)
        val textViewLocation = view.findViewById<TextView>(R.id.textViewLocation)
        rvEpisodes = view.findViewById(R.id.rvEpisodes)

        imageViewCharacter.load(character.characterImage) {
            transformations(CircleCropTransformation())
        }

        textViewName.text = character.characterName
        textViewStatus.text = character.status
        textViewSpecies.text = character.species
        textViewGender.text = character.gender
        textViewOrigin.text = character.origin.name
        textViewLocation.text = character.location.name

        episodeAdapter = EpisodeAdapter(episodesList)
        rvEpisodes.layoutManager = LinearLayoutManager(requireContext())
        rvEpisodes.adapter = episodeAdapter

        fetchEpisodes()
    }

    private fun fetchEpisodes() {

        val episodeIds = character.episodes.map { url ->
            url.substringAfterLast("/")
        }

        val idsParam = episodeIds.joinToString(separator = ",")


        RickAndMortyBaseApiClient.rickAndMortyCharactersApiService.getEpisodes(idsParam).enqueue(object : Callback<List<Episode>> {
            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                if (response.isSuccessful) {
                    response.body()?.let { episodes ->
                        episodesList.clear()
                        episodesList.addAll(episodes)
                        episodeAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("EpisodeFetch", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                Log.e("EpisodeFetch", "Fallo al obtener episodios", t)
            }
        })
    }
}
