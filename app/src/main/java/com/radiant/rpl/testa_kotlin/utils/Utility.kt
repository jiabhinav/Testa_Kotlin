package com.radiant.rpl.testa_kotlin.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object Utility
     {

         fun callReplaceFragment(navHostFragment: NavHostFragment?, id: Int, bundle: Bundle?) {
             val fragmentManager: FragmentManager = navHostFragment!!.childFragmentManager

             val loginFragment: Fragment? = fragmentManager.getPrimaryNavigationFragment()

             if (loginFragment != null) {
                 if (bundle==null)
                 {
                     NavHostFragment.findNavController(loginFragment).navigate(id)
                 }
                 else
                 {
                     NavHostFragment.findNavController(loginFragment).navigate(id,bundle)
                    /* if(id== R.id.nav_userresultview && fragment_id==1)
                     {
                         val navBuilder = NavOptions.Builder().setPopUpTo(R.id.nav_assignedbatch, false).build()
                         NavHostFragment.findNavController(loginFragment).navigate(id,bundle,navBuilder)
                     }
                     else if (id==R.id.nav_userresultview && fragment_id==2)
                     {
                         val navBuilder = NavOptions.Builder().setPopUpTo(R.id.nav_assignedbatch, false).build()
                         NavHostFragment.findNavController(loginFragment).navigate(id,bundle,navBuilder)
                     }
                     else
                     {
                         NavHostFragment.findNavController(loginFragment).navigate(id,bundle)
                     }*/


                 }

             }
         }




         fun getScreenWidth(): Int {
             return Resources.getSystem().getDisplayMetrics().widthPixels
         }

         fun getScreenHeight(): Int {
             return Resources.getSystem().displayMetrics.heightPixels
         }


         fun getDate(strCurrentDate: String?): String? {
             var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
             var newDate: Date? = null
             var date: String? = ""
             try {
                 newDate = format.parse(strCurrentDate)
                 format = SimpleDateFormat("dd MMM yy, hh:mm a")
                 date = format.format(newDate)
             } catch (e: ParseException) {
                 e.printStackTrace()
             }
             return date
         }

         fun showToast(context: Activity, mesg:String,gravity:Int)
         {
             if (gravity==1)
             {
                 val t= Toast.makeText(context, mesg, Toast.LENGTH_SHORT)
                 t.setGravity(Gravity.TOP,0,0)
                 t.show()
             }
             else if (gravity==2)
             {
                 val t= Toast.makeText(context, mesg, Toast.LENGTH_SHORT)
                 t.setGravity(Gravity.CENTER,0,0)
                 t.show()
             }
             else
             {
                 val t= Toast.makeText(context, mesg, Toast.LENGTH_SHORT)
                 t.setGravity(Gravity.BOTTOM,0,0)
                 t.show()
             }

         }


         fun getDeviceName(): String {
             val manufacturer = Build.MANUFACTURER
             val model = Build.MODEL
             return if (model.startsWith(manufacturer)) {
                 capitalize(model)
             } else {
                 capitalize(manufacturer) + " " + model
             }
         }

         // FOR DEVICE-NAME
         private fun capitalize(s: String?): String {
             if (s == null || s.length == 0) {
                 return ""
             }
             val first = s[0]
             return if (Character.isUpperCase(first)) {
                 s
             } else {
                 first.uppercaseChar().toString() + s.substring(1)
             }
         }


     }


fun getCurrentDate():String{
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return sdf.format(Date())
}




    fun requestPermission(activity: Activity?, listPermissionsNeeded: List<String>, PERMISSION_REQUEST_CODE: Int) {
        ActivityCompat.requestPermissions(activity!!, listPermissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
    }






    fun setIconImageSize(imageView: ImageView, width: Int, height: Int) {
        imageView.layoutParams.width = width
        imageView.layoutParams.height = height
    }

    fun setImageTint(context: Context?, imageView: ImageView, color: Int) {
        imageView.setColorFilter(ContextCompat.getColor(context!!, color))
    }

    fun setDrawableLeftTint(context: Context?, textView: TextView, color: Int) {
        textView.setTextColor(ContextCompat.getColor(context!!, color))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (drawable in textView.compoundDrawables) {
                if (drawable != null) {
                    drawable.colorFilter =
                        PorterDuffColorFilter(
                            ContextCompat.getColor(textView.context, color),
                            PorterDuff.Mode.SRC_IN
                        )
                }
            }
        }
    }

    fun setDrawableLeft(context: Context?, textView: TextView, id: Int) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, id, 0, 0)
    }

     fun setTextColor(context: Context?, textView: TextView,color:Int)
     {
         textView.setTextColor(ContextCompat.getColor(context!!, color))
     }
     fun setViewBg(context: Context?, view: View, color:Int)
     {
         view.setBackgroundColor(ContextCompat.getColor(context!!, color))
     }


     fun setDrawableRight(context: Context?, textView: TextView, id: Int,color:Int) {
         textView.setTextColor(ContextCompat.getColor(context!!, color))
         textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, id, 0)
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             for (drawable in textView.compoundDrawables) {
                 if (drawable != null) {
                     drawable.colorFilter =
                         PorterDuffColorFilter(
                             ContextCompat.getColor(textView.context, color),
                             PorterDuff.Mode.SRC_IN
                         )
                 }
             }
         }
     }

    fun setImage(context: Context, imageView: ImageView, drawble: Int) {
        imageView.setImageDrawable(context.resources.getDrawable(drawble))
    }



    fun createPartFromString(string: String): RequestBody {
        //return RequestBody.create(MultipartBody.FORM, string)
        return RequestBody.create(MediaType.parse("text/plain"), string)
    }
    fun isValidEmail(target: CharSequence): Boolean {
        val match1 = target.toString()
        var check = false
        if (match1.contains(".")) {
            val extension = match1.substring(match1.lastIndexOf("."))
            Log.d("extensionnnn", "isValidEmail: $extension")
            if (extension == ".com" || extension == ".in" || extension == ".net" || extension == ".online" || extension == ".in") {
                check = true
            }
        } else {
            check = false
            Log.d("extensionnnn22", "isValidEmail: $match1")
        }
        return check
    }


    private fun textcolorStar(value:String,changableText:String): Spanned? {
        val next = "<font color='#EE0000'>"+changableText+"</font>"
       return Html.fromHtml(value + next)
    }


    fun rotateImage(source: Bitmap?, angle: Float?): Bitmap? {
        return try {
            val matrix = Matrix()
            matrix.postRotate(angle!!)
            Bitmap.createBitmap(
                    source!!, 0, 0, source.width, source.height,
                    matrix, true
            )
        } catch (e: Exception) {
            source
        }
    }


    fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }



    fun <T> getPayload(payloadClass: Class<T>?, jsonData: String?): T {
        return Gson().fromJson(jsonData, payloadClass)
    }




        fun getDate(strCurrentDate: String?): String? {
            var format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            var newDate: Date? = null
            var date: String? = ""
            try {
                newDate = format.parse(strCurrentDate)
                format = SimpleDateFormat("dd MMM yy, hh:mm a")
                date = format.format(newDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }





