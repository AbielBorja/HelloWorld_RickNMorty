package com.example.rickandmorty.ui.fragments

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.ui.fragmentsviewmodels.EpisodeListViewModel

class CharacterDetailFragment : Fragment() {

    companion object {
        private const val ARG_CHARACTER = "character"
        fun newInstance(character: Character?) = CharacterDetailFragment().apply {
            arguments = Bundle().apply { putParcelable(ARG_CHARACTER, character) }
        }
    }

    private var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments?.getParcelable(ARG_CHARACTER)
        Log.d("CharacterDetailFragment", "Character recibido: $character")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        character?.let { char ->

            view.findViewById<ImageView>(R.id.imageViewCharacter).load(char.characterImage) {
                placeholder(R.drawable.baseline_accessibility_24)
                error(R.drawable.baseline_clear_24)
            }
            view.findViewById<TextView>(R.id.textViewName).text = char.characterName
            view.findViewById<TextView>(R.id.textViewStatus).text = "Status: ${char.status}"
            view.findViewById<TextView>(R.id.textViewSpecies).text = "Species: ${char.species}"
            view.findViewById<TextView>(R.id.textViewGender).text = "Gender: ${char.gender}"
            view.findViewById<TextView>(R.id.textViewOrigin).text = "Origin: ${char.origin.name}"
            view.findViewById<TextView>(R.id.textViewLocation).text = "Location: ${char.location.name}"
        }
    }
}