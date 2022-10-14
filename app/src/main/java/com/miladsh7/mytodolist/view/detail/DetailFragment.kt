package com.miladsh7.mytodolist.view.detail

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.data.model.TodoEntity
import com.miladsh7.mytodolist.databinding.CustomDialogDeleteBinding
import com.miladsh7.mytodolist.databinding.FragmentDetailBinding
import com.miladsh7.mytodolist.utils.EDIT
import com.miladsh7.mytodolist.utils.NEW
import com.miladsh7.mytodolist.utils.showIcon
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
    private val args: DetailFragmentArgs by navArgs()

    private val todoColorAdapter by lazy {
        TodoColorAdapter(generateColors()) {
            setTodoColorBackgroundColor(it)
        }
    }

    private var selectionColorId = 0
    private var todoId = 0
    private var type = ""

    @Inject
    lateinit var todoEntity: TodoEntity

    lateinit var bindingDelete: CustomDialogDeleteBinding

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

            setTodoColorBackgroundColor(Selection.BLUE)

            val todoID = args.entity
            type = if (todoID != null) {
                EDIT
            } else {
                NEW
            }

            if (type == EDIT) {
                if (todoID != null) {
                    edtTitle.setText(todoID.title)
                    edtDescription.setText(todoID.desc)
                    txtDateTimeDetail.text = todoID.calendar
                    selectionColorId = todoID.selectionId
                    todoColorAdapter.setSelected(selectionColorId)
                    setTodoColorBackgroundColor(getSelection(selectionColorId))

                    txtTitle.setText(R.string.MyTodolist_title_toolbar_edit_detail)
                    imgDeleteEdit.showIcon(true)
                    imgShareEdit.showIcon(true)

                    imgDeleteEdit.setOnClickListener {
                        val dialogDelete =
                            Dialog(requireContext(), R.style.customAlertDialogThemOverly)
                        bindingDelete = CustomDialogDeleteBinding.inflate(layoutInflater)
                        dialogDelete.setContentView(bindingDelete.root)
                        dialogDelete.show()

                        bindingDelete.apply {
                            txtTitleDelete.text =
                                resources.getString(R.string.todolist_title_dialog_edit_detail)

                            positiveBtn.text =
                                resources.getString(R.string.MyTodolist_title_buttonPositive_dialog)
                            negativeBtn.text =
                                resources.getString(R.string.todolist_title_buttonNegative_dialog)

                            positiveBtn.setOnClickListener {
                                viewModel.delete(todoID)
                                dialogDelete.dismiss()
                                val action =
                                    DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                                findNavController().navigate(action)
                            }
                            negativeBtn.setOnClickListener {
                                dialogDelete.cancel()
                            }
                        }
                    }

                    imgShareEdit.setOnClickListener {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "${resources.getString(R.string.MyTodolist_title_detail)} : ${todoID.title} " +
                                        "\n ${resources.getString(R.string.MyTodolist_description_detail)} : ${todoID.desc} \n ${todoID.calendar}"
                            )
                            type = ("text/plain")
                        }
                        startActivity(intent)
                    }
                }
            } else {
                txtTitle.setText(R.string.MyTodolist_title_toolbar_addNote_detail)
            }

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
                if (type == NEW) {
                    if (edtTitle.text?.isNotEmpty() == true) {
                        viewModel.insert(todoEntity)
                    }
                } else {
                    binding.apply {
                        todoID?.title = edtTitle.text.toString()
                        todoID?.desc = edtDescription.text.toString()
                        todoID?.calendar = todoEntity.calendar
                        todoID?.selectionId = selectionColorId
                    }
                    if (todoID != null) {
                        viewModel.update(todoID)
                    }
                }
            }
        }
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }

    private fun getSelection(id: Int): Selection {
        return when (id) {
            0 -> Selection.BLUE
            1 -> Selection.ORANGE
            2 -> Selection.PINK
            3 -> Selection.PURPLE
            4 -> Selection.RED
            5 -> Selection.GREEN
            else -> Selection.BLUE
        }
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