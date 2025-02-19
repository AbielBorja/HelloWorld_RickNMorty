package com.example.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R

class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): LoadingViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.loading_item, parent, false)
            return LoadingViewHolder(view)
        }
    }
}