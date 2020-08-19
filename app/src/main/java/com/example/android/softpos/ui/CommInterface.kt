package com.example.android.softpos.ui

interface CommInterface {
    fun onSuccess(UID:String)
    fun onFail(errorMessage:String)

}