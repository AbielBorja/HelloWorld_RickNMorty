package com.example.rickandmorty.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.ui.Adapters.EpisodeAdapter
import com.example.rickandmorty.network.RickAndMortyBaseApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailFragment : Fragment() {

    private lateinit var character: Character
    private lateinit var episodesRecyclerView: RecyclerView
    private lateinit var episodeAdapter: EpisodeAdapter
    private val episodesList: MutableList<com.example.rickandmorty.model.Episode> = mutableListOf()

    companion object {
        private const val ARG_CHARACTER = "character"

        fun newInstance(character: Character?) = CharacterDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_CHARACTER, character)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments?.getParcelable(ARG_CHARACTER)
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

        val characterImageView = view.findViewById<ImageView>(R.id.imageViewCharacter)
        val nameTextView = view.findViewById<TextView>(R.id.textViewName)
        val statusTextView = view.findViewById<TextView>(R.id.textViewStatus)
        val speciesTextView = view.findViewById<TextView>(R.id.textViewSpecies)
        val genderTextView = view.findViewById<TextView>(R.id.textViewGender)
        val originTextView = view.findViewById<TextView>(R.id.textViewOrigin)
        val locationTextView = view.findViewById<TextView>(R.id.textViewLocation)
        episodesRecyclerView = view.findViewById(R.id.rvEpisodes)

        // Load character image with Coil
        characterImageView.load(character.characterImage) {
            transformations(CircleCropTransformation())
        }

        // Set character details
        nameTextView.text = character.characterName
        statusTextView.text = character.status
        speciesTextView.text = character.species
        genderTextView.text = character.gender
        originTextView.text = character.origin.name
        locationTextView.text = character.location.name

        // Setup episodes list and adapter
        episodeAdapter = EpisodeAdapter(episodesList)
        episodesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        episodesRecyclerView.adapter = episodeAdapter

        fetchEpisodes()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchEpisodes() {
        // Extract episode IDs from URLs
        val episodeIds = character.episodes.map { url -> url.substringAfterLast("/") }
        val idsParam = episodeIds.joinToString(separator = ",")

        // Use lifecycleScope to perform the network call off the main thread
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Assume getEpisodes is a suspend function in your API service
                val episodes = withContext(Dispatchers.IO) {
                    RickAndMortyBaseApiClient.rickAndMortyCharactersApiService.getEpisodes(idsParam)
                }
                episodesList.clear()
                episodesList.addAll(episodes)
                episodeAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Log.e("EpisodeFetch", "Failed to fetch episodes", e)
            }
        }
    }
}
