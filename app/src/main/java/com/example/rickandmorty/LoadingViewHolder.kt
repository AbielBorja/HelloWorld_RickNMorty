package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): LoadingViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.loading_item, parent, false)
            return LoadingViewHolder(view)
        }
    }
}
    