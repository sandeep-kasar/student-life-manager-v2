package com.studentlifemanager.database.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 *  Entity class for pin
 *  here we are creating table for pin
 *
 *  pnLinkType, 1 = web link, 2 = video link
 *
 * @author SandeepK
 * @version 2.0
 * */
@Entity(tableName = "pin")
@Parcelize
data class PinEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pn_id", defaultValue = "0")
    val pnID: Int = 0,
    @ColumnInfo(name = "pn_title")
    val pnTitle: String,
    @ColumnInfo(name = "pn_subject")
    val pnSubject: String,
    @ColumnInfo(name = "pn_link")
    val pnLink: String,
    @ColumnInfo(name = "pn_link_type")
    val pnLinkType: Int,
    @ColumnInfo(name = "pn_date")
    val pnDate: String
) : Parcelable