package com.miladsh7.mytodolist.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.databinding.FragmentHomeBinding
import com.miladsh7.mytodolist.utils.showInVisible
import com.miladsh7.mytodolist.view.adapter.TodoAdapter
import com.miladsh7.mytodolist.view.base.BaseFragment
import com.miladsh7.mytodolist.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home,
    FragmentHomeBinding::class
) {

    private val viewModel: TodoViewModel by viewModels()

    lateinit var todoAdapter: TodoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            todoAdapter = TodoAdapter()
            viewModel.todoLiveData.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    todoAdapter.setData(it)
                    rvItemHome.layoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    rvItemHome.adapter = todoAdapter
                    rvItemHome.showInVisible(true)
                    emptyLay.showInVisible(false)

                } else {
                    emptyLay.showInVisible(true)
                    rvItemHome.showInVisible(false)
                }
            }
            extButtonAdd.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(entity = null)
                findNavController().navigate(action)
            }
        }
    }
}