package com.example.ianklobe_musicapp.view

import com.example.ianklobe_musicapp.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ianklobe_musicapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

// This array holds the string values for the names of the tabs
val  musicArray = arrayOf(
    "Rock",
    "Classic",
    "Pop"
)

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        // Fully setting up the tabs
        TabLayoutMediator(binding.tabLayout,  binding.viewPager) { tab, position ->
            tab.text = musicArray[position]
            when(position) {
                0 -> tab.setIcon(R.drawable.flame_rock)
                1 -> tab.setIcon(R.drawable.poke_classic)
                2 -> tab.setIcon(R.drawable.note_pop)
            }
        }.attach()
    }
}