package com.example.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.model.Character
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmorty.R

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.name)
    private val imageView: ImageView = itemView.findViewById(R.id.image)

    fun bind(character: Character) {
        nameTextView.text = character.characterName
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
