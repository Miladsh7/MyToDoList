package com.miladsh7.mytodolist.utils

import android.view.View

fun View.showInVisible(isShown: Boolean) {
    if (isShown) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}
