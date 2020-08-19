package com.example.android.softpos.ui.transactionHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.softpos.R
import com.example.android.softpos.data.model.db.Transaction
import kotlinx.android.synthetic.main.transaction_list_item.view.*

class TransactionAdapter(transactionArg:List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    private var data: List<Transaction> = transactionArg

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        return TransactionHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_list_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<Transaction>) {
        this.data = data
        notifyDataSetChanged()
    }

    class TransactionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Transaction) = with(itemView) {
            itemView.creditCardUID_value_tv.text = item.creditCardUID
            itemView.date_value_tv.text = item.date
            itemView.fees_value_tv.text = item.fees

        }
    }
}