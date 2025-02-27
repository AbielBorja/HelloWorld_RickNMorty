package com.example.rickandmorty.ui.ViewHoders

import android.content.Intent
import android.util.Log
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
import com.example.rickandmorty.ui.CharacterDetailActivity

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

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, CharacterDetailActivity::class.java)
            intent.putExtra("character", character)
            itemView.context.startActivity(intent)
            Log.d("CharacterViewHolder", "Iniciando CharacterDetailActivity con: $character")
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
            return CharacterViewHolder(view)
        }
    }


}
