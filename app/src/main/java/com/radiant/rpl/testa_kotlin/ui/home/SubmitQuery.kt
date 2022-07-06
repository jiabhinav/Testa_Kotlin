package com.radiant.rpl.testa_kotlin.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.radiant.rpl.testa_kotlin.databinding.FragmentSubmitQueryBinding
import com.radiant.rpl.testa_kotlin.model.ModelSubmitQuery
import com.radiant.rpl.testa_kotlin.network.ApiCallListener
import com.radiant.rpl.testa_kotlin.network.ApiClient
import com.radiant.rpl.testa_kotlin.network.RetrofitApiCall
import com.radiant.rpl.testa_kotlin.session.SesssionManager.getUsername
import com.radiant.rpl.testa_kotlin.session.SesssionManager.sessionInit
import com.radiant.rpl.testa_kotlin.utils.ALertChooseFile
import com.radiant.rpl.testa_kotlin.utils.Constant.FILE_TYPE
import com.radiant.rpl.testa_kotlin.utils.Constant.REQUEST_TYPE
import com.radiant.rpl.testa_kotlin.utils.ProcessingDialog.dismiss
import com.radiant.rpl.testa_kotlin.utils.ProcessingDialog.showProgressDialog
import com.radiant.rpl.testa_kotlin.utils.RunTimePermission.checkRaadWrite
import com.radiant.rpl.testa_kotlin.utils.Utility.showToast
import com.radiant.rpl.testa_kotlin.utils.setLocalImage
import java.io.ByteArrayOutputStream


class SubmitQuery : Fragment(), ALertChooseFile.GetImage, ApiCallListener {

    lateinit var binding:FragmentSubmitQueryBinding
    var image_type = 1
    var typeApi=-1
    var requestType=1
    private val PICK_IMAGE1 = 100
    private val PICK_IMAGE2 = 1
    private val PICK_IMAGE3 = 2
    private val MY_CAMERA_PERMISSION_CODE = 1000
    var imageUri1: Uri? = null
    var imageUri2:Uri? = null
    var imageUri3:Uri? = null
    var screenshot1: String? = null
    var screenshot2:String? = null
    var screenshot3:String? = null
    var getproblem:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSubmitQueryBinding.inflate(inflater, container, false)
        sessionInit(requireActivity())
        init()
        return binding.root
    }

    fun init()
    {
        binding.etproblem.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().length <= 500) {
                    binding.limit.setText(s.toString().length.toString() + "/" + "500")
                } else {
                    showToast(requireActivity(),"You can not add more than 500 character ",2)
                }
            }
        })

        binding.img11.setOnClickListener(View.OnClickListener {
        if (checkRaadWrite(requireActivity()))
        {
            image_type = 1
            getImage()
            //Opengallery()
        }

    })

        binding.img22.setOnClickListener(View.OnClickListener {

            if (checkRaadWrite(requireActivity()))
            {
                image_type = 2
              //  Opengallery()
                getImage()
            }
        })

        binding.img33.setOnClickListener(View.OnClickListener {

            if (checkRaadWrite(requireActivity())) {
                image_type = 3
                getImage()
                //Opengallery()
            }
        })

        binding.submitbtn.setOnClickListener(View.OnClickListener {
            if (binding.textname.text.toString() == "" && binding.textname.text.toString().length < 3) {

                showToast(requireActivity(),"Enter full name",2)

            } else if (binding.textmobile.text.toString() == "" && binding.textmobile.getText().toString().length < 10) {

                showToast(requireActivity(),"Enter 10 Digit mobile number",2)

            } else if (binding.etproblem.text.toString() == "") {

                showToast(requireActivity(),"Enter your inquiries",2)

            } else {
                SaveDetail()
            }
        })

    }

    private fun SaveDetail() {
        typeApi=1
       showProgressDialog(requireActivity())
        val params = LinkedHashMap<String, String>()
        params.put("help_text",binding.etproblem.text.toString())
        params.put("name",binding.textname.text.toString())
        params.put("email",binding.textemail.text.toString())
        params.put("mobile",binding.textmobile.text.toString())
        params.put("user_name",getUsername()!!)
        if (screenshot1!=null)
        {
            params.put("image_one",screenshot1!!)
        }
        if (screenshot2!=null)
        {
            params.put("image_two",screenshot2!!)
        }
        if (screenshot3!=null)
        {
            params.put("image_three",screenshot3!!)
        }

        Log.d("w22efff", "SaveDetail: "+params.toString())

        RetrofitApiCall.hitApi(ApiClient.apiInterFace.save_help_data(params), this, typeApi)

    }
    override fun onSuccess(response: String, responseCode: Int, requestCode: Int) {
       dismiss()
        if (typeApi==1)
        {
            Log.d("dashboardwddfd", "onSuccess: "+ response)
            val resp = RetrofitApiCall.getPayload(ModelSubmitQuery::class.java, response)

            if(resp.status==1)
            {
                showToast(requireActivity(),resp.msg,2)
                requireActivity().onBackPressed()
            }
            else
            {
                showToast(requireActivity(),resp.msg,2)
            }
        }
    }

    override fun onError(response: String?, requestCode: Int) {
        dismiss()
    }

    fun getImage()
    {
        val with: ImagePicker.Builder = ImagePicker.with(this)
        with.crop()
        with.compress(1024)
        with.maxResultSize(1080, 1080)
        with.createIntent { Intent: Intent? ->
            startForCameraResult.launch(Intent)
            null
        }
    }

    override fun picimage(bitmap: Bitmap) {
        Log.d("nbirbnrbim", "picimage: "+bitmap)
        if (image_type == 1) {
            screenshot1 = getBase64(bitmap)
            binding.img11.setImageBitmap(bitmap)
        } else if (image_type == 2) {
            screenshot2 = getBase64(bitmap)
            binding.img22.setImageBitmap(bitmap)
        } else if (image_type == 3) {
            screenshot3 = getBase64(bitmap)
            binding.img33.setImageBitmap(bitmap)
        }
    }

    override fun aadharFront(bitmap: Bitmap) {

    }

    override fun aadharBack(bitmap: Bitmap) {

    }




    private val startForCameraResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            if (resultCode == Activity.RESULT_OK) {
                val data:Intent? = result.data
                if (data!=null)
                {
                    val uri = data.data
                    Log.d("mProfileUri", ": "+uri)
                    val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri)
                    if(image_type==1)
                    {
                        binding.img11.setLocalImage(uri!!, false)
                        //binding.img11.setImageBitmap(bitmap)
                        screenshot1=getBase64(bitmap)
                    }
                    else if (image_type==2)
                    {
                        binding.img22.setLocalImage(uri!!, false)
                        screenshot2=getBase64(bitmap)
                    }
                    else if(image_type==3)
                    {
                        binding.img33.setLocalImage(uri!!, false)
                        screenshot3=getBase64(bitmap)

                    }



                }

            }
            else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


    fun getBase64(bitmap: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

}