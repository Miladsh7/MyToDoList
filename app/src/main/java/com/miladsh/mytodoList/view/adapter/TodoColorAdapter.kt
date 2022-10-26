package com.miladsh.mytodoList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.miladsh.mytodoList.R
import com.miladsh.mytodoList.databinding.ItemColorTodoBinding
import com.miladsh.mytodoList.view.detail.Selection

class TodoColorAdapter(
    private val colors: List<Selection>,
    private val onItemClick: (Selection) -> Unit
) : RecyclerView.Adapter<TodoColorAdapter.ViewHolder>() {

    private var checkedPosition = colors.indexOf(Selection.BLUE)

    fun setSelected(position: Int) {
        checkedPosition = position
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemColorTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Selection) {
            binding.apply {

                if (checkedPosition == -1) {
                    tickIcon.isVisible = false
                } else {
                    tickIcon.isVisible = checkedPosition == adapterPosition
                }

                strokeCard.setCardBackgroundColor(getColorFromItem(item))
                backgroundCard.setCardBackgroundColor(getColorFromItem(item))

                strokeCard.setOnClickListener {
                    tickIcon.isVisible = true
                    if (checkedPosition != adapterPosition) {
                        notifyItemChanged(checkedPosition)
                        checkedPosition = adapterPosition
                        onItemClick(item)
                    }
                }
            }
        }

        private fun getColorFromItem(item: Selection): Int {
            return when (item) {
                Selection.GREEN -> ContextCompat.getColor(
                    itemView.context,
                    R.color.MyTodolist_color_green
                )
                Selection.RED -> ContextCompat.getColor(
                    itemView.context,
                    R.color.MyTodolist_color_red
                )
                Selection.PURPLE -> ContextCompat.getColor(
                    itemView.context,
                    R.color.MyTodolist_color_purple
                )
                Selection.PINK -> ContextCompat.getColor(
                    itemView.context,
                    R.color.MyTodolist_color_pink
                )
                Selection.ORANGE -> ContextCompat.getColor(
                    itemView.context,
                    R.color.MyTodolist_color_orange
                )
                Selection.BLUE -> ContextCompat.getColor(
                    itemView.context,
                    R.color.MyTodolist_color_blue
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemColorTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}