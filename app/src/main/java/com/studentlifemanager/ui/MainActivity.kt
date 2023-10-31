package com.studentlifemanager.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.studentlifemanager.R
import com.studentlifemanager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * This is the main activity of the app
 * All other fragments are implemented inside this activity
 *
 *
 * @author SandeepK
 * @version 2.0
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        //setup bottom navigation
        binding.navView.setupWithNavController(
            findNavController(R.id.nav_host_fragment_activity_main)
        )
    }
}