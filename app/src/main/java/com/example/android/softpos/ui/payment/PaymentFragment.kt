package com.example.android.softpos.ui.payment

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.softpos.R
import com.example.android.softpos.data.model.db.Transaction
import com.example.android.softpos.ui.CommInterface
import com.example.android.softpos.ui.InterfaceHelper
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.android.synthetic.main.fragment_payment.view.*
import java.text.SimpleDateFormat
import java.util.*


class PaymentFragment(private var fees:String) : DialogFragment(),CommInterface {
    private lateinit var viewModel: PaymentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        //region initialization
        InterfaceHelper.paymentCommInterface = this

        //hide outer background of cardView
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //endregion

        //set Fees sent through class arguments
        view.fees_tv.text = fees


        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[PaymentViewModel::class.java]
    }



    override fun onSuccess(UID:String) {
        //Log.d("PaymentFragment", "onSuccess: $UID")
        val simpleDateFormat = SimpleDateFormat("hh:mm aa MMM dd, yyyy")
        val formattedDate = simpleDateFormat.format(Calendar.getInstance().time)
        viewModel.insert(Transaction(creditCardUID= UID ,fees = fees,date = formattedDate))

        viewModel.allTransactions.observe(this, androidx.lifecycle.Observer {
            Log.d("PaymentFragment", "onSuccess: $it")
            Log.d("PaymentFragment", "onSuccess: ${it.size}")
        })

        if(context != null) {
            actionButton.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
            actionButton.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_success)
            actionButton.text = getString(R.string.paymentSuccess)
            actionButton.isClickable = true
            actionButton.setOnClickListener { dismiss() }
        }
    }

    override fun onFail(errorMessage: String) {
        Log.d("PaymentFragment", "onFail: $errorMessage")
        Toast.makeText(activity,errorMessage,Toast.LENGTH_SHORT).show()
        if(context != null) {
            actionButton.setBackgroundColor(ContextCompat.getColor(context!!, R.color.Red_Error))
            actionButton.icon = ContextCompat.getDrawable(context!!, R.drawable.ic_fail)
            actionButton.text = getString(R.string.paymentFail)
        }
    }

    override fun onResume() {
        super.onResume()
        InterfaceHelper.paymentCommInterface = this
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        InterfaceHelper.paymentCommInterface = null
    }


}