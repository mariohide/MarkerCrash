package code.bug.map

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import code.bug.map.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.initPager()
    }

    private fun ActivityMainBinding.initPager() {
        tvFrag1.setOnClickListener { viewPager.currentItem = 0 }
        tvFrag2.setOnClickListener { viewPager.currentItem = 1 }

        viewPager.isUserInputEnabled = false
        viewPager.isUserInputEnabled = false
        viewPager.isNestedScrollingEnabled = false
        viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        viewPager.adapter = object : FragmentStateAdapter(this@MainActivity) {
            override fun getItemCount() = 2

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> ComposeMapFragment.newInstance()
                    1 -> WhatEverFragment.newInstance()
                    else -> throw IllegalArgumentException()
                }
            }

        }
    }
}