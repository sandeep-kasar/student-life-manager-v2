package com.studentlifemanager.utils

import android.content.Context
import android.os.Build
import android.view.View
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

object ViewExtensions{

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

    fun Fragment.getColor(@ColorRes colorRes: Int) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ContextCompat.getColor(requireContext(), colorRes)
    } else {
        resources.getColor(colorRes)
    }

    /**
     * Easy toast function for Activity.
     */
    fun FragmentActivity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }
}