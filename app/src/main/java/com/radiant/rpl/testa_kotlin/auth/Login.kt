package com.radiant.rpl.testa_kotlin.auth
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.radiant.rpl.testa_kotlin.R
import com.radiant.rpl.testa_kotlin.databinding.ActivityLoginBinding
import com.radiant.rpl.testa_kotlin.model.ModelUser
import com.radiant.rpl.testa_kotlin.network.ApiCallListener
import com.radiant.rpl.testa_kotlin.network.ApiClient
import com.radiant.rpl.testa_kotlin.network.RetrofitApiCall
import com.radiant.rpl.testa_kotlin.session.SesssionManager.sessionInit
import com.radiant.rpl.testa_kotlin.session.SesssionManager.sessionLogin
import com.radiant.rpl.testa_kotlin.utils.ProcessingDialog.dismiss
import com.radiant.rpl.testa_kotlin.utils.ProcessingDialog.showProgressDialog
import com.radiant.rpl.testa_kotlin.utils.Utility.showToast


class Login : AppCompatActivity(),View.OnClickListener, ApiCallListener {
    var typeApi=-1
    lateinit var binding :ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionInit(this)
        init()
    }
    fun init()
    {
        binding.loginButton.setOnClickListener(this)
    }




    override fun onClick(v: View?) {
        if(v?.id==R.id.loginButton)
        {
            if (binding.username.text.toString().equals(""))
            {
                showToast(this,getString(R.string.username),2)
            }
            else if(binding.password.text.toString().equals(""))
            {
                showToast(this,getString(R.string.password),2)
            }
            else
            {
                loginNow()
            }
        }
    }

   /* {








        device_name = Realme RMX3171,

    }*/

    private fun loginNow() {
        typeApi=1
        showProgressDialog(this)
        val params = LinkedHashMap<String, String>()
        params.put("user_name",binding.username.text.toString())
        params.put("password",binding.password.text.toString())
        params.put("device_version","11")
        params.put("device_name"," Realme RMX3171")

        params.put("company_id","null")
        params.put("activity","User Login")
        params.put("app_version","1.0")
        params.put("device_id","21eefc7fd401760d")
        params.put("ip","192.168 .0 .28")

        params.put("mobile_imei","21eefc7fd401760d")
        params.put("longi","Longitude: 77.31511333333333")
        params.put("lat","Lattitude: 28.649099999999997")
        params.put("key_salt","UmFkaWFudEluZm9uZXRTYWx0S2V5")

        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.login(params),
            this, typeApi
        )
    }
    override fun onSuccess(response: String, responseCode: Int, requestCode: Int) {
        dismiss()
        if (typeApi==1)
        {
            val resp = RetrofitApiCall.getPayload(ModelUser::class.java, response)
            Log.d("dashboardwddfd", "onSuccess: "+ Gson().toJson(resp))
            if(resp.status==1)
            {
                configSignIn(resp)

            }
            else
            {
               showToast(this,resp.msg,2)
            }
        }
    }

    override fun onError(response: String?, requestCode: Int) {
        dismiss()
    }

    //  EXTRA
    private fun configSignIn(modelUser: ModelUser) {

            if (modelUser.user_type.equals("Assessor", ignoreCase = true)) {
                //Its Hold-
              /*  com.radiant.rpl.testa.ui.Initials.NewSignUpActivity.SaveAssessorAsyncTask(email)
                    .execute()*/
            } else if (modelUser.user_type.equals("Student", ignoreCase = true)) {

                if (modelUser.exam_status.equals("Not Attempted")) {

                    sessionLogin(modelUser)
                        if (modelUser.student_details.user_name.equals("aman")) {
                           /* val intent = Intent(this@Login, WelcomePageActivity::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                            finish()*/
                        } else {
                            setResult(101, Intent())
                            finish()
                           // callReplaceFragment(navHostFragment, R.id.nav_welcomepage, null)
                          /*  val intent = Intent(this@Login, WelcomePageActivity::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)*/

                        }

                } else if (modelUser.exam_status .equals("Attempted")) {
                        /*if (modelUser.student_details.user_name.equals("aman")) {
                            val intent = Intent(this@Login, WelcomePageActivity::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                            finish()
                        } else {
                            val intent = Intent(this@Login, WelcomePageActivity::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                            finish()
                        }*/
                    }
                }
            }



}