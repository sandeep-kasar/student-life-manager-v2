package com.studentlifemanager.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 *  Some useful extension function
 *
 * */

object ViewExtensions {

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun Context.getColorCompat(@ColorRes colorRes: Int) =
        ContextCompat.getColor(this, colorRes)

    fun Fragment.getColor(@ColorRes colorRes: Int) =
        ContextCompat.getColor(requireContext(), colorRes)

    fun FragmentActivity.getColor(@ColorRes colorRes: Int) =
        ContextCompat.getColor(this, colorRes)

    /**
     * Easy toast function for Activity.
     */
    fun FragmentActivity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    /**
     * Easy toast function for Fragment.
     */
    fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireActivity(), text, duration).show()
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}