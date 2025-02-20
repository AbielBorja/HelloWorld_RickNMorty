package com.example.rickandmorty.ui.Adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.ui.ViewHoders.CharacterViewHolder
import com.example.rickandmorty.ui.ViewHoders.LoadingViewHolder

class MainAdapter(private val charactersList: MutableList<Character>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_ITEM) CharacterViewHolder.create(parent) else LoadingViewHolder.create(parent)

    override fun getItemViewType(position: Int) = if (position < charactersList.size) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder && position < charactersList.size) {
            holder.bind(charactersList[position])
        }
    }

    override fun getItemCount() = charactersList.size + if (isLoading) 1 else 0

    fun appendData(newCharacters: List<Character>) {
        val startPos = charactersList.size
        charactersList.addAll(newCharacters)
        notifyItemRangeInserted(startPos, newCharacters.size)
    }

    fun updateData(newList: List<Character>) {
        charactersList.clear()
        charactersList.addAll(newList)
        notifyDataSetChanged()
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
