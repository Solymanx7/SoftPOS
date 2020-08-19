package com.example.android.softpos.ui.nfc

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel

class NFCViewModel: ViewModel() {
    lateinit var tag:Tag
    private val isValid = true // dummy constant
    var errorMessage:String = "dummy errorMessage"

    //dummy function that could be replaced by a real function to validate card depending on card tag
    fun isValidCard() = isValid

    //retrieve Hexadecimal UID
    fun getNfcUID():String{

        val tagId = tag.id
        val sb = StringBuilder()
        for (byte in tagId) {
            sb.append(String.format("%02X", byte))
        }

        val nfcUID = sb.toString()

        Log.d("NFCViewModel", "UID $nfcUID")
        return nfcUID
    }
}