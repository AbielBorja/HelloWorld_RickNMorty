package com.example.rickandmorty.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.model.Character
import android.view.ViewGroup

class MainAdapter(
    private val charactersList: MutableList<Character>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

    private var isLoading: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            CharacterViewHolder.create(parent)
        } else {
            LoadingViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < charactersList.size) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder) {
            holder.bind(charactersList[position])
        }
    }

    override fun getItemCount(): Int {
        return charactersList.size + if (isLoading) 1 else 0
    }

    fun appendData(newCharacters: List<Character>) {
        val startPos = charactersList.size
        charactersList.addAll(newCharacters)
        notifyItemRangeInserted(startPos, newCharacters.size)
    }

    fun showLoading() {
        if (!isLoading) {
            isLoading = true
            notifyItemInserted(charactersList.size)
        }
    }

    fun hideLoading() {
        if (isLoading) {
            isLoading = false
            notifyItemRemoved(charactersList.size)
        }
    }
}










