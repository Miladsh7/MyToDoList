package com.miladsh7.mytodolist.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView

fun View.showInVisible(isShown: Boolean) {
    if (isShown) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun AppCompatImageView.showIcon(isShown: Boolean) {
    if (isShown) {
        this.visibility = AppCompatImageView.VISIBLE
    } else {
        this.visibility = AppCompatImageView.INVISIBLE
    }
}