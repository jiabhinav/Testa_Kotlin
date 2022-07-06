package com.radiant.rpl.testa_kotlin.network


interface ApiCallListener {
    fun onSuccess(response: String, responseCode: Int, requestCode: Int)
    fun onError(response: String?, requestCode: Int)
}


