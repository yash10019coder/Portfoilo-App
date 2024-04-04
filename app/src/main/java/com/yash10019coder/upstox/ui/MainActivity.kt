package com.yash10019coder.upstox.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yash10019coder.upstox.R
import com.yash10019coder.upstox.databinding.ActivityMainBinding
import com.yash10019coder.upstox.ui.holdings.HoldingsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.also {
            title = getString(R.string.app_bar_title)
        }


        val holdingsFragment: Fragment = HoldingsFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, holdingsFragment)
            .commit()
    }
}

