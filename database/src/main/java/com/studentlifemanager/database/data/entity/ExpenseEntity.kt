package com.studentlifemanager.database.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 *  Entity class for my expense
 *  here we are creating table for my expense
 *
 * @author SandeepK
 * @version 2.0
 * */
@Entity(tableName = "my_expense")
@Parcelize
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ex_id", defaultValue = "0")
    val exId: Int = 0,
    @ColumnInfo(name = "ex_amount")
    val exAmount: Long,
    @ColumnInfo(name = "ex_title")
    val exTitle: String,
    @ColumnInfo(name = "ex_note")
    val exNote: String,
    @ColumnInfo(name = "ex_type")
    val exType: Int,
    @ColumnInfo(name = "ex_date")
    val exDate: String,
    @ColumnInfo(name = "ex_month")
    val exMonth: Int
) : Parcelable