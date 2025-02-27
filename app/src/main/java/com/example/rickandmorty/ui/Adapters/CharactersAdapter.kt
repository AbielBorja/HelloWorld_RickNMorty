package com.example.rickandmorty.ui.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.ui.ViewHoders.CharacterViewHolder
import com.example.rickandmorty.ui.ViewHoders.LoadingViewHolder

class CharactersAdapter(private val characters: MutableList<Character>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_ITEM) {
            CharacterViewHolder.create(parent)
        } else {
            LoadingViewHolder.create(parent)
        }

    override fun getItemViewType(position: Int) =
        if (position < characters.size) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder && position < characters.size) {
            holder.bind(characters[position])
        }
    }

    override fun getItemCount() = characters.size + if (isLoading) 1 else 0

    fun appendCharacters(newCharacters: List<Character>) {
        val startPosition = characters.size
        characters.addAll(newCharacters)
        notifyItemRangeInserted(startPosition, newCharacters.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCharacters(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    fun showLoading() {
        if (!isLoading) {
            isLoading = true
            notifyItemInserted(characters.size)
        }
    }

    fun hideLoading() {
        if (isLoading) {
            isLoading = false
            notifyItemRemoved(characters.size)
        }
    }
}
