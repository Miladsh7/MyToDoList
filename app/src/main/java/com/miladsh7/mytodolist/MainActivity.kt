package com.miladsh7.mytodolist

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.miladsh7.mytodolist.databinding.ActivityMainBinding
import com.miladsh7.mytodolist.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_container)
    }
}