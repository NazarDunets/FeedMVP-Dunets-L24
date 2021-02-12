package com.example.feedmvp_dunets_l24

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feedmvp_dunets_l24.databinding.ActivityMainBinding
import com.example.feedmvp_dunets_l24.view.FeedFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        startFeedFragment()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun startFeedFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, FeedFragment.newInstance())
            .commit()
    }
}

