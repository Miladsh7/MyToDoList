package com.miladsh7.mytodolist.view.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.databinding.FragmentHomeBinding
import com.miladsh7.mytodolist.view.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home,
    FragmentHomeBinding::class
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.extButtonAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            findNavController().navigate(action)
        }
    }
}