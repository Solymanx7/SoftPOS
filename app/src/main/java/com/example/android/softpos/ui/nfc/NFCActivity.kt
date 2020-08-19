package com.example.android.softpos.ui.nfc

import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android.softpos.ui.InterfaceHelper


class NFCActivity : AppCompatActivity() {
    private lateinit var viewModel: NFCViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[NFCViewModel::class.java]

        Log.d("Test", "onCreate: NFC Intent fired")
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {

            viewModel.tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)!!

            if(viewModel.isValidCard()){
                InterfaceHelper.paymentCommInterface?.onSuccess(viewModel.getNfcUID())
            }
            else{
                InterfaceHelper.paymentCommInterface?.onFail(viewModel.errorMessage)
            }

        }
        finish()
    }


/*
    if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
        finish()
        return
    }
*/


}