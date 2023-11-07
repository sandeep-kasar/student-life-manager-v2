package com.studentlifemanager.common.utils

/**
 * This is the interface to handle recyclerview row click
 *
 *
 * @author SandeepK
 * @version 2.0
 * */
interface IRecyclerViewClickListener {
    fun <T> onItemClick(data: T)
}