package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.ui.fragments.CharacterDetailFragment

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        if (savedInstanceState == null) {
            val character = intent.getParcelableExtra<Character>("character")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CharacterDetailFragment.newInstance(character))
                .commit()
        }
    }
}