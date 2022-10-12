package com.miladsh7.mytodolist.view.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.miladsh7.mytodolist.R
import com.miladsh7.mytodolist.data.model.TodoEntity
import com.miladsh7.mytodolist.databinding.ItemTodoBinding
import com.miladsh7.mytodolist.view.detail.Selection

class TodoAdapter() : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var items: List<TodoEntity> = listOf()

    fun setData(items: List<TodoEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: TodoEntity) {
            binding.apply {
                txtTitle.text = item.title
                txtDescription.text = item.desc ?: ""
                if (txtDescription.text.isNotEmpty() ){
                    txtDescription.visibility = View.VISIBLE
                }else{
                    txtDescription.visibility = View.GONE
                }

                when (item.selectionId) {
                    Selection.BLUE.ordinal -> {
                        txtTitle.setTextAppearance(R.style.TextAppearanceBold_ItemTitle_Blue)
                        lineItem.setCardBackgroundColor(itemView.context.getColor(R.color.MyTodolist_color_blue))
                        cardItem.apply {
                            setBackgroundColor(context.getColor(R.color.MyTodolist_backgroundItem_blue))
                            strokeColor =
                                ContextCompat.getColor(context, R.color.MyTodolist_strokeItem_blue)
                        }
                    }
                    Selection.ORANGE.ordinal -> {
                        txtTitle.setTextAppearance(R.style.TextAppearanceBold_ItemTitle_Orange)
                        lineItem.setCardBackgroundColor(itemView.context.getColor(R.color.MyTodolist_color_orange))
                        cardItem.apply {
                            setBackgroundColor(context.getColor(R.color.MyTodolist_backgroundItem_orange))
                            strokeColor =
                                ContextCompat.getColor(context, R.color.MyTodolist_strokeItem_orange)
                        }
                    }
                    Selection.PINK.ordinal -> {
                        txtTitle.setTextAppearance(R.style.TextAppearanceBold_ItemTitle_Pink)
                        lineItem.setCardBackgroundColor(itemView.context.getColor(R.color.MyTodolist_color_pink))
                        cardItem.apply {
                            setBackgroundColor(context.getColor(R.color.MyTodolist_backgroundItem_pink))
                            strokeColor =
                                ContextCompat.getColor(context, R.color.MyTodolist_strokeItem_pink)
                        }
                    }
                    Selection.PURPLE.ordinal -> {
                        txtTitle.setTextAppearance(R.style.TextAppearanceBold_ItemTitle_Purple)
                        lineItem.setCardBackgroundColor(itemView.context.getColor(R.color.MyTodolist_color_purple))
                        cardItem.apply {
                            setBackgroundColor(context.getColor(R.color.MyTodolist_backgroundItem_purple))
                            strokeColor =
                                ContextCompat.getColor(context, R.color.MyTodolist_strokeItem_purple)
                        }
                    }
                    Selection.RED.ordinal -> {
                        txtTitle.setTextAppearance(R.style.TextAppearanceBold_ItemTitle_Red)
                        lineItem.setCardBackgroundColor(itemView.context.getColor(R.color.MyTodolist_color_red))
                        cardItem.apply {
                            setBackgroundColor(context.getColor(R.color.MyTodolist_backgroundItem_red))
                            strokeColor =
                                ContextCompat.getColor(context, R.color.MyTodolist_strokeItem_red)
                        }
                    }
                    Selection.GREEN.ordinal -> {
                        txtTitle.setTextAppearance(R.style.TextAppearanceBold_ItemTitle_Green)
                        lineItem.setCardBackgroundColor(itemView.context.getColor(R.color.MyTodolist_color_green))
                        cardItem.apply {
                            setBackgroundColor(context.getColor(R.color.MyTodolist_backgroundItem_green))
                            strokeColor =
                                ContextCompat.getColor(context, R.color.MyTodolist_strokeItem_green)
                        }
                    }
                    else -> {
                        println("item can't without any color")
                    }
                }
            }
        }
    }
}