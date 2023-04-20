package com.test.placeholdercats.utils

import android.os.Build
import android.os.Bundle
import android.view.View
import java.io.Serializable

fun View.hidden() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.show(show: Boolean) {
    if (show)
        this.show()
    else
        this.hidden()
}

fun <T : Serializable?> Bundle.getSerializableCompat(key: String, clazz: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializable(key, clazz)!! else (getSerializable(key) as T)
}