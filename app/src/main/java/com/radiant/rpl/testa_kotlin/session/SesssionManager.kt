package com.radiant.rpl.testa_kotlin.session


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log


import com.google.gson.Gson
import com.radiant.rpl.testa_kotlin.MainActivity
import com.radiant.rpl.testa_kotlin.auth.Login
import com.radiant.rpl.testa_kotlin.model.ModelUser


 object SesssionManager {

        lateinit var sp_login: SharedPreferences
        lateinit var sp_editor: SharedPreferences.Editor
        var USER_JSON = "userlogin"
        var USER_Address = "UserAddress"
        var LANGUAGE = "lang"
        var AUTH_TOKEN = ""

    fun sessionInit(context: Context) {
         sp_login = context.getSharedPreferences("info_testa", Context.MODE_PRIVATE)
         sp_editor = sp_login.edit()
    }

    fun sessionLogin(user: ModelUser?) {
        val userdata = Gson().toJson(user)
        sp_editor.putString(USER_JSON, userdata)
        sp_editor.commit()

    }

        fun removeAddress() {
            sp_editor.putString(USER_Address, null)
            sp_editor.commit()
        }

        fun setLang(lang: String) {

            sp_editor.putString(LANGUAGE, lang)
            sp_editor.commit()
        }

        fun getLanguage(): String? {
            return sp_login.getString(LANGUAGE, null)
        }


        fun checkLogin(context: Context) {
            if (isLoggedIn() == null) {
                val loginsucces = Intent(context, Login::class.java)
                loginsucces.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                context.startActivity(loginsucces)

            } else {
                 val loginsucces = Intent(context, MainActivity::class.java)
                    loginsucces.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    context.startActivity(loginsucces)


            }
        }

        fun isLoggedIn(): String? {
            return sp_login.getString(USER_JSON, null)
        }


        fun getValueString(KEY_NAME: String): String? {
            return sp_login.getString(KEY_NAME, "")
        }

        fun logoutSession(context: Context) {
            sp_editor.clear()
            sp_editor.commit()
            val logout = Intent(context, Login::class.java)
            logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(logout)
        }


        fun getUser():ModelUser? {

            try {
                val gson = Gson()
                val list = gson.fromJson(sp_login.getString(USER_JSON, "0"), ModelUser::class.java)
                Log.d("ddfefefef", "getUserDetails: " + Gson().toJson(list))
                // list = gson.fromJson(new Gson().toJson(sp_login.getString(USER_JSON, "0")), new TypeToken< UserModel.User>(){}.getType());
                return list
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

     fun getUsername():String? {

         try {
             val gson = Gson()
             val list = gson.fromJson(sp_login.getString(USER_JSON, "0"), ModelUser::class.java)
             Log.d("ddfefefef", "getUserDetails: " + Gson().toJson(list))
             // list = gson.fromJson(new Gson().toJson(sp_login.getString(USER_JSON, "0")), new TypeToken< UserModel.User>(){}.getType());
             return list.student_details.user_name
         } catch (e: Exception) {
             e.printStackTrace()
         }
         return null
     }



    }









