package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.ui.adapters.CharactersAdapter

class ListOfCharactersMainActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    // Renamed ViewModel to a more descriptive name (assumes you renamed it below)
    private val viewModel by lazy { ViewModelProvider(this).get(CharactersViewModel::class.java) }

    // Holds the complete list of characters for filtering
    private val allCharacters = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.charactersRv)
        searchView = findViewById(R.id.search)
        charactersAdapter = CharactersAdapter(mutableListOf())
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = charactersAdapter
        recyclerView.addOnScrollListener(createScrollListener())
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterCharacters(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterCharacters(newText)
                return true
            }
        })
    }

    private fun filterCharacters(query: String?) {
        if (query.isNullOrEmpty()) {
            charactersAdapter.updateCharacters(ArrayList(allCharacters))
        } else {
            val filteredList = allCharacters.filter {
                it.characterName.contains(query, ignoreCase = true)
            }
            charactersAdapter.updateCharacters(filteredList)
        }
    }

    private fun observeViewModel() {
        viewModel.pageCharactersLiveData.observe(this) { newCharacters ->
            charactersAdapter.hideLoading()
            allCharacters.addAll(newCharacters)
            val currentQuery = searchView.query.toString()
            if (currentQuery.isNotEmpty()) {
                filterCharacters(currentQuery)
            } else {
                charactersAdapter.appendCharacters(newCharacters)
            }
        }
    }

    private fun createScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
            val lastVisibleItem = layoutManager.findLastVisibleItemPositions(null).maxOrNull() ?: 0
            if (lastVisibleItem >= layoutManager.itemCount - 4 && viewModel.hasMorePages) {
                charactersAdapter.showLoading()
                viewModel.fetchCharacters()
            }
        }
    }
}
