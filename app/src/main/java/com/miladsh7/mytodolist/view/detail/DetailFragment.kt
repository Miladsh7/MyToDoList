package com.miladsh7.mytodolist.view.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.data.model.TodoEntity
import com.miladsh7.mytodolist.databinding.FragmentDetailBinding
import com.miladsh7.mytodolist.view.adapter.TodoColorAdapter
import com.miladsh7.mytodolist.view.base.BaseFragment
import com.miladsh7.mytodolist.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(
    R.layout.fragment_detail,
    FragmentDetailBinding::class
) {

    private val viewModel: TodoViewModel by viewModels()

    private val todoColorAdapter by lazy {
        TodoColorAdapter(generateColors()) {
            setTodoColorBackgroundColor(it)
        }
    }

    private var selectionColorId = 0
    private var todoId = 0

    @Inject
    lateinit var todoEntity: TodoEntity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {

            txtTitle.setText(R.string.MyTodolist_title_toolbar_addNote_detail)

            imgBackDetail.setOnClickListener {
                val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                findNavController().navigate(action)
            }

            val flexboxLayoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
            }

            allIconSelect.layoutManager = flexboxLayoutManager
            allIconSelect.adapter = todoColorAdapter

            btnSave.setOnClickListener {
                todoEntity.id = todoId
                todoEntity.title = edtTitle.text.toString()
                todoEntity.desc = edtDescription.text.toString()
                todoEntity.selectionId = selectionColorId

                if (edtTitle.length() > 0) {
                    val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                    findNavController().navigate(action)

                } else {
                    Toast.makeText(requireContext(), "عنوان نباید خالی باشد", Toast.LENGTH_SHORT)
                        .show()
                    imgBackDetail.setOnClickListener {
                        findNavController().enableOnBackPressed(false)
                    }
                }
                viewModel.insert(todoEntity)
            }
        }
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }

    private fun generateColors() = listOf(
        Selection.BLUE,
        Selection.ORANGE,
        Selection.PINK,
        Selection.PURPLE,
        Selection.RED,
        Selection.GREEN,
    )

    private fun setTodoColorBackgroundColor(selection: Selection) {
        selectionColorId = selection.ordinal
        val color = when (selection) {
            Selection.BLUE -> ContextCompat.getColor(
                requireContext(),
                R.color.MyTodolist_backgroundItem_blue
            )
            Selection.RED -> ContextCompat.getColor(
                requireContext(),
                R.color.MyTodolist_backgroundItem_red
            )
            Selection.PURPLE -> ContextCompat.getColor(
                requireContext(),
                R.color.MyTodolist_backgroundItem_purple
            )
            Selection.PINK -> ContextCompat.getColor(
                requireContext(),
                R.color.MyTodolist_backgroundItem_pink
            )
            Selection.ORANGE -> ContextCompat.getColor(
                requireContext(),
                R.color.MyTodolist_backgroundItem_orange
            )
            Selection.GREEN -> ContextCompat.getColor(
                requireContext(),
                R.color.MyTodolist_backgroundItem_green
            )
        }
        binding.cardColors.setCardBackgroundColor(color)
    }
}