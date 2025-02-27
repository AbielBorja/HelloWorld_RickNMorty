package com.example.rickandmorty.ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Episode

class EpisodeAdapter(private val episodes: List<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val episodeNameTextView: TextView = itemView.findViewById(R.id.tvEpisodeName)
        val airDateTextView: TextView = itemView.findViewById(R.id.tvAirDate)
        val episodeCodeTextView: TextView = itemView.findViewById(R.id.tvEpisodeCode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episode, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.episodeNameTextView.text = episode.name
        holder.airDateTextView.text = episode.air_date
        holder.episodeCodeTextView.text = episode.episode
    }

    override fun getItemCount(): Int = episodes.size
}
