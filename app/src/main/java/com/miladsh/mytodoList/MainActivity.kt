package com.miladsh.mytodoList

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.miladsh.mytodoList.databinding.ActivityMainBinding
import com.miladsh.mytodoList.view.base.BaseActivity
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