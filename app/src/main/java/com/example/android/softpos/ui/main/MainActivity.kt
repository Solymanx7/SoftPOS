package com.example.android.softpos.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android.softpos.R
import com.example.android.softpos.ui.payment.PaymentFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), TextWatcher {
    private var defaultCurrency = "$"
    private lateinit var previousFees: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region SDK level check for getting default currency
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            val current = resources.configuration.locales.get(0)
            val deviceCurrency = Currency.getInstance(current).symbol
            defaultCurrency = deviceCurrency
        }
        //endregion

        //region Fees edittext
        fees_et.setText(getString(R.string.fees_value, defaultCurrency))
        fees_et.requestFocus() // To request focus at activity startup, some custom keyboards doesn't respect "stateVisible"
        fees_et.addTextChangedListener(this)
        fees_et.setOnEditorActionListener { textView, i, keyEvent ->
            //check when user presses on check mark on keyboard and make sure value isn't the default or 0
            if (i == EditorInfo.IME_ACTION_DONE && fees_et.text.toString() != getString(R.string.fees_value, defaultCurrency)) {
                val dialogFragment = PaymentFragment(fees_et.text.toString())
                val ft = supportFragmentManager.beginTransaction()
                val prev: Fragment? = supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")

            }
            false /*for hiding keyboard after pressing done*/
        }
        //endregion

    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        previousFees = fees_et.text.toString()
        //Log.d("MainActivity", "beforeTextChanged: $previousFees")
    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

        //region Corner cases
        // reset text when the user remove the whole text
        if (fees_et.text.toString().isEmpty()) {
            Log.d("MainActivity", "onTextChanged: inside")
            fees_et.removeTextChangedListener(this)
            fees_et.setText(getString(R.string.fees_value, defaultCurrency))
            fees_et.setSelection(fees_et.length())
            fees_et.addTextChangedListener(this)
        }

        //prevent the user from adding any numbers after currency or deleting "."
        if (fees_et.text?.get(0).toString() != defaultCurrency) {
            fees_et.removeTextChangedListener(this)
            fees_et.setText(previousFees)
            fees_et.setSelection(fees_et.length())
            fees_et.addTextChangedListener(this)
        }
        if (!fees_et.text.toString().contains(".")){
            fees_et.removeTextChangedListener(this)
            fees_et.setText(previousFees)
            fees_et.setSelection(fees_et.length())
            fees_et.addTextChangedListener(this)
        }
        //endregion

        //region Adding numbers from most right
        if (fees_et.text.toString().length > previousFees.length && fees_et.text.toString()
                .last() != previousFees.last() || fees_et.text.toString()
                .substringAfter(".").length > 2
        ) {
            fees_et.removeTextChangedListener(this)
            val amount = fees_et.text?.toString()?.substringAfter(defaultCurrency)
                ?.toDouble()?.times(10.0)
            //Log.d("MainActivity", "onTextChanged: $currency")
            val rounded = String.format("%.2f", amount).toDouble()
            val newAmount =
                defaultCurrency + rounded
            //Log.d("MainActivity", "onTextChanged before change: $newAmount the s $s")
            //Log.d("MainActivity", "onTextChanged: $newAmount")
            fees_et.setText(newAmount)
            fees_et.setSelection(newAmount.length)
            //Log.d("MainActivity", "onTextChanged: Length:${newAmount.length}")
            fees_et.addTextChangedListener(this)


        }
        //endregion

        //region Deleting numbers from most right
        if (fees_et.length() < previousFees.length) {
            //reset to original fees value
            if (previousFees == getString(
                    R.string.fees_value,
                    defaultCurrency
                )
            ) {
                fees_et.removeTextChangedListener(this)
                fees_et.setText(getString(R.string.fees_value, defaultCurrency))
                fees_et.setSelection(fees_et.length())
                fees_et.addTextChangedListener(this)
            }

            //divide numbers by 10
            if (
                fees_et.text.toString()
                    .last() != previousFees.last()
            ) {


                fees_et.removeTextChangedListener(this)

                val amount = fees_et.text?.toString()?.substringAfter(defaultCurrency)
                    ?.toDouble()?.div(10.0)
                val rounded = String.format("%.2f", amount).toDouble()
                val newAmount =
                    defaultCurrency + rounded
               /* Log.d(
                    "MainActivity",
                    "onTextChanged: old amount: ${fees_et.text.toString()} new amount : $newAmount"
                )*/
                if (amount == 0.0) {
                    fees_et.setText(getString(R.string.fees_value, defaultCurrency))
                } else {
                    fees_et.setText(newAmount)
                }

                fees_et.setSelection(fees_et.text.toString().length)
                fees_et.addTextChangedListener(this)

            }
        }


        //endregion


    }
}