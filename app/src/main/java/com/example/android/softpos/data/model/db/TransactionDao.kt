package com.example.android.softpos.data.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {
    @Query("SELECT * from transaction_table ORDER BY date DESC")
    fun getAlphabetizedTransactions(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: Transaction)

    @Query("DELETE FROM transaction_table")
    suspend fun deleteAll()
}