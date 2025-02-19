package com.example.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView = view.findViewById(R.id.name)
    private val speciesTextView: TextView = view.findViewById(R.id.species)
    private val imageView: ImageView = view.findViewById(R.id.image)

    fun bind(character: Character) {
        nameTextView.text = character.characterName
        speciesTextView.text = character.species
        imageView.load(character.characterImage) {
            transformations(CircleCropTransformation())
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
            return CharacterViewHolder(view)
        }
    }
}
