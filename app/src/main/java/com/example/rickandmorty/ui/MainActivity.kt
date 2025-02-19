package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmorty.R

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.charactersRv)
        adapter = MainAdapter(mutableListOf())
        recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(createScrollListener())
    }

    private fun observeViewModel() {
        viewModel.pageCharactersLiveData.observe(this) { newCharacters ->
            adapter.hideLoading()
            adapter.appendData(newCharacters)
        }
    }

    private fun createScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
            val lastVisibleItem = layoutManager.findLastVisibleItemPositions(null).maxOrNull() ?: 0
            if (lastVisibleItem >= layoutManager.itemCount - 4 && viewModel.hasMorePages) {
                adapter.showLoading()
                viewModel.fetchCharacters()
            }
        }
    }
}