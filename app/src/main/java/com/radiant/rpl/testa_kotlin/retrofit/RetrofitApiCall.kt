package com.radiant.rpl.testa_kotlin.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.radiant.rpl.testa_kotlin.R
import com.radiant.rpl.testa_kotlin.application.App
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class RetrofitApiCall {
    companion object {
        fun hitApi(
            apiCall: Call<ResponseBody>,
            apiCallListener: ApiCallListener,
            apiRequestCode: Int
        ) {
            val connectivity = App.getInstance().applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivity.activeNetworkInfo
            if (networkInfo == null || !networkInfo.isAvailable || !networkInfo.isConnected) {
                apiCallListener.onError(
                    App.activity?.getString(R.string.noInternetMsg),
                    apiRequestCode
                )
                return
            }
            apiCall.enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.e("Api Url: ", (call.request() as Request).url().toString())
                    Log.e("Api Status/Message: ", "${response.code()} ${response.message()}")
                    when (response.code()) {
                        200 -> {
                            val responseBody = response.body()!!.string().trim { it <= ' ' }
                            Log.e("onResponse", responseBody)
                            apiCallListener.onSuccess(responseBody, response.code(), apiRequestCode)
                        }
                        else -> {
                            val responseBody = response.errorBody()!!.string().trim { it <= ' ' }
                            Log.e("onResponse", responseBody)
                            apiCallListener.onSuccess(responseBody, response.code(), apiRequestCode)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("onFailure Exception", t.message.toString())
                    apiCallListener.onError(t.message.toString(), apiRequestCode)
                }
            })
        }

        fun <T> getPayloadAsList(listType: Type, jsonData: String): List<T>? {
            return Gson().fromJson<List<T>>(jsonData, listType)
        }

        fun <T> getPayload(payloadClass: Class<T>, jsonData: String): T {
            return Gson().fromJson(jsonData, payloadClass)
        }

        fun <T> getPayload(payloadClass: Class<T>, jsonData: JsonReader): T {
            return Gson().fromJson(jsonData, payloadClass)
        }
    }
}


