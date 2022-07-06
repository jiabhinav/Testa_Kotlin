package com.radiant.rpl.testa_kotlin.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("android_connect1/check_login_test_wd17.php")
    fun login(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("android_connect/save_help_data.php")
    fun save_help_data(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("saveHelpSupport")
    fun saveHelpSupport(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("saveInquiry")
    fun saveInquiry(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>




    @POST("shippingAddressList")
    fun shippingAddressList(
    ): Call<ResponseBody>


    @POST("dashboard")
    fun dashboard(
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("productDetail")
    fun productDetail(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("articleDetail")
    fun articleDetail(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("addToCart")
    fun addToCart(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @POST("myProfile")
    fun myProfile(
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("productList")
    fun productList(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("productSearch")
    fun productSearch(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>



    @FormUrlEncoded
    @POST("cartList")
    fun cartList(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("orderPlaced")
    fun orderPlaced(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("deleteFromCart")
    fun deleteFromCart(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("updateCart")
    fun updateCart(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>




    @FormUrlEncoded
    @POST("generateOtp")
    fun generateOtp(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("verifyOtp")
    fun verifyOtp(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>






    @FormUrlEncoded
    @POST("deleteShippingAddress")
    fun deleteShippingAddress(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("saveProductList")
    fun saveProductList(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("articleList")
    fun articleList(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("orderList")
    fun orderList(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("deleteSaveProduct")
    fun deleteSaveProduct(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("updateToken")
    fun updateToken(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("saveProduct")
    fun saveProduct(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("checkOrderStock")
    fun checkOrderStock(
        @FieldMap bodyParams: LinkedHashMap<String, String>
    ): Call<ResponseBody>



}