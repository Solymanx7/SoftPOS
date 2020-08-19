package com.example.android.softpos.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transaction_table")
data class Transaction(@PrimaryKey (autoGenerate = true) var id:Int = 0, val creditCardUID:String, val fees:String, val date:String)