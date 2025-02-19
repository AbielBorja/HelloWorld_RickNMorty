package com.example.rickandmorty.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.ui.fragmentsviewmodels.EpisodeListViewModel
import com.example.rickandmorty.R

class EpisodeListFragment : Fragment() {

    companion object {
        fun newInstance() = EpisodeListFragment()
    }

    private val viewModel: EpisodeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_episode_list, container, false)
    }
}