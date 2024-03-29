package com.example.android.softpos.ui.payment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.softpos.data.model.db.Transaction
import com.example.android.softpos.data.model.db.TransactionRepository
import com.example.android.softpos.data.model.db.TransactionRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(application:Application) : AndroidViewModel(application) {

    private val repository: TransactionRepository

    val allTransactions: LiveData<List<Transaction>>

    init {
        val transactionsDao = TransactionRoomDatabase.getDatabase(application,viewModelScope).transactionDao()
        repository = TransactionRepository(transactionsDao)
        allTransactions = repository.allTransactions
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(transaction)
    }
}