package com.miladsh7.mytodolist.view.home

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.data.model.TodoEntity
import com.miladsh7.mytodolist.databinding.CustomDialogDeleteBinding
import com.miladsh7.mytodolist.databinding.FragmentHomeBinding
import com.miladsh7.mytodolist.utils.EDIT
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
    private val todoAdapter by lazy {
        TodoAdapter { todoEntity: TodoEntity, s: String ->
            onItemClick(todoEntity, s)
        }
    }

    lateinit var bindingDeleteAll: CustomDialogDeleteBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
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

                imgDeleteAll.isEnabled = it.isNotEmpty()
                imgDeleteAll.background =
                    if (it.isNotEmpty()) ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.delete
                    )
                    else ContextCompat.getDrawable(requireContext(), R.drawable.delete_gray)
            }

            imgDeleteAll.setOnClickListener {
                val dialogDeleteAll = Dialog(requireContext(), R.style.customAlertDialogThemOverly)
                bindingDeleteAll = CustomDialogDeleteBinding.inflate(layoutInflater)
                dialogDeleteAll.setContentView(bindingDeleteAll.root)
                dialogDeleteAll.show()

                bindingDeleteAll.apply {
                    txtTitleDelete.text =
                        resources.getString(R.string.MyTodolist_title_dialog_deleteAll_home)

                    positiveBtn.text =
                        resources.getString(R.string.MyTodolist_title_buttonPositive_deleteAll_dialog)
                    negativeBtn.text =
                        resources.getString(R.string.MyTodolist_title_buttonNegative_dialog)

                    positiveBtn.setOnClickListener {
                        viewModel.deleteAll()
                        dialogDeleteAll.dismiss()
                        emptyLay.showInVisible(true)
                    }

                    negativeBtn.setOnClickListener {
                        dialogDeleteAll.cancel()
                    }
                }

            }
            extButtonAdd.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(entity = null)
                findNavController().navigate(action)
            }
        }
    }

    private fun onItemClick(todoEntity: TodoEntity, state: String) {
        if (state == EDIT) {
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(todoEntity)
            findNavController().navigate(action)
        }
    }
}