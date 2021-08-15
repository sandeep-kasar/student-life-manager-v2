package com.studentlifemanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 *  Entity class for my expense
 *  here we are creating table for my expense
 *
 * @author SandeepK
 * @version 2.0
 * */
@Entity(tableName = "my_expense")
data class MyExpenseEntity (
    @PrimaryKey(autoGenerate = true)
    val ex_amount : Long,
    val ex_title : String,
    val ex_note :String,
    val ex_type : Int,
    val date : String,
    val month : Int
)