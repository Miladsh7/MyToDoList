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

val Number.withPersianDigits: String
    get() = "$this".withPersianDigits

val String.withPersianDigits: String
    get() = StringBuilder().also { builder ->
        toCharArray().forEach {
            builder.append(
                when {
                    Character.isDigit(it) -> PERSIAN_DIGITS["$it".toInt()]
                    it == '.' -> "/"
                    else -> it
                }
            )
        }
    }.toString()

private val PERSIAN_DIGITS = charArrayOf(
    '0' + 1728,
    '1' + 1728,
    '2' + 1728,
    '3' + 1728,
    '4' + 1728,
    '5' + 1728,
    '6' + 1728,
    '7' + 1728,
    '8' + 1728,
    '9' + 1728
)