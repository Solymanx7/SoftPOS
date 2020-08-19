package com.example.android.softpos.ui.transactionHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.softpos.R
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager:LinearLayoutManager
    private lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        val viewModel = ViewModelProvider(this)[TransactionViewModel::class.java]

        //region Address Recycler View
        //viewModel.deleteAll()
        //endregion

        clear_all_btn.setOnClickListener { viewModel.deleteAll() }

        viewModel.allTransactions.observe(this, androidx.lifecycle.Observer {
            linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            transaction_recyclerView.layoutManager = linearLayoutManager
            adapter = TransactionAdapter(it)
            transaction_recyclerView.adapter = adapter
        })
    }
}