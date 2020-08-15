package com.example.android.softpos.ui.payment

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.softpos.R
import kotlinx.android.synthetic.main.pending_payment.view.*


class PaymentFragment(var fees:String) : DialogFragment() {
    private lateinit var viewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pending_payment, container, false)
        //hide outer background of cardview
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //set Fees sent through class arguments
        view.fees_tv.text = fees


        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("PaymentFragment", "onActivityCreated: hey")
        viewModel = ViewModelProviders.of(this).get(PaymentViewModel::class.java)
        // TODO: Use the ViewModel
    }

    /* override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val builder = AlertDialog.Builder(activity!!)
      val inflater = activity!!.layoutInflater
      val view: View = inflater.inflate(R.layout.pending_payment, null)

      builder.setView(view)
      val dialog: Dialog = builder.create()

      dialog.window!!.setBackgroundDrawable(
          ColorDrawable(Color.TRANSPARENT)
      )

      return dialog
  }*/


}