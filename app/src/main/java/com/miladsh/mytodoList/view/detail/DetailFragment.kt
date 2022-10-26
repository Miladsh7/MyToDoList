package com.miladsh.mytodoList.view.detail

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Scroller
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.miladsh.mytodoList.R
import com.miladsh.mytodoList.data.model.TodoEntity
import com.miladsh.mytodoList.databinding.CustomDialogDeleteBinding
import com.miladsh.mytodoList.databinding.FragmentDetailBinding
import com.miladsh.mytodoList.utils.*
import com.miladsh.mytodoList.view.adapter.TodoColorAdapter
import com.miladsh.mytodoList.view.base.BaseFragment
import com.miladsh.mytodoList.viewmodel.TodoViewModel
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

    lateinit var titleClear: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val gregorian = Gregorian()
        val calendar = gregorian.getCurrentSolarDate()

        binding.apply {

            txtTitle.setText(R.string.MyTodolist_title_toolbar_addNote_detail)

            txtDateTimeDetail.text = calendar

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

            if (type == NEW) {
                showKeyboard()
                edtTitle.requestFocus()
            } else {
                hideKeyboard()
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
                    edtTitle.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.edt_title_detail_edit_background
                    )

                    imgDeleteEdit.setOnClickListener {
                        val dialogDelete =
                            Dialog(requireContext(), R.style.customAlertDialogThemOverly)
                        bindingDelete = CustomDialogDeleteBinding.inflate(layoutInflater)
                        dialogDelete.setContentView(bindingDelete.root)
                        dialogDelete.show()

                        bindingDelete.apply {
                            txtTitleDelete.text =
                                resources.getString(R.string.MyTodolist_title_dialog_edit_detail)

                            positiveBtn.text =
                                resources.getString(R.string.MyTodolist_title_buttonPositive_dialog)
                            negativeBtn.text =
                                resources.getString(R.string.MyTodolist_title_buttonNegative_dialog)

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
                edtTitle.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.edt_title_detail_background
                )
            }

            imgClear.setOnClickListener {
                titleClear = edtTitle.setText("").toString()
                imgBackDetail.setOnClickListener {
                    findNavController().enableOnBackPressed(false)
                }
                Toast.makeText(requireContext(), "عنوان جدیدی رو وارد کنید", Toast.LENGTH_SHORT)
                    .show()

            }

            btnSave.setOnClickListener {
                todoEntity.id = todoId
                todoEntity.title = edtTitle.text?.trim().toString()
                todoEntity.desc = edtDescription.text.toString()
                todoEntity.selectionId = selectionColorId
                todoEntity.calendar = calendar

                if (edtTitle.length() > 0 && edtTitle.text!!.isNotBlank()) {
                    val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                    findNavController().navigate(action)

                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.MyTodolist_msg_detail,
                        Toast.LENGTH_SHORT
                    ).show()

                    imgBackDetail.setOnClickListener {
                        findNavController().enableOnBackPressed(false)
                    }
                }
                if (type == NEW) {
                    if (edtTitle.text?.isNotEmpty() == true) {
                        if (edtTitle.text!!.isNotBlank()){
                            viewModel.insert(todoEntity)
                        }
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
            edtDescription.setOnTouchListener(OnTouchListener { view, event ->
                if (view.id == R.id.edt_description) {
                    view.parent.requestDisallowInterceptTouchEvent(true)
                    when (event.action and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_MOVE -> view.parent.requestDisallowInterceptTouchEvent(
                            false
                        )
                    }
                }
                false
            })
            edtDescription.setScroller(Scroller(requireContext()))

            requireActivity().onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().popBackStack()
                    }
                })
        }
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