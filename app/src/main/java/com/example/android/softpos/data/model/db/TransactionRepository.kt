package com.example.android.softpos.data.model.db

import androidx.lifecycle.LiveData

class TransactionRepository(private val transactionDao: TransactionDao) {

    val allTransactions: LiveData<List<Transaction>> = transactionDao.getAlphabetizedTransactions()

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun deleteAll(){
        transactionDao.deleteAll()
    }


}
