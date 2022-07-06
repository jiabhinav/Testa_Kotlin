package com.radiant.rpl.testa_kotlin.utils


import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.radiant.rpl.testa_kotlin.R
import com.radiant.rpl.testa_kotlin.application.App
import com.radiant.rpl.testa_kotlin.databinding.ActivityAlertChooseFileBinding
import com.radiant.rpl.testa_kotlin.utils.Constant.CAMERA_REQUEST
import com.radiant.rpl.testa_kotlin.utils.Constant.FILE_TYPE
import com.radiant.rpl.testa_kotlin.utils.Constant.MY_CAMERA_PERMISSION_CODE
import com.radiant.rpl.testa_kotlin.utils.Constant.PICK_IMAGE_ALTERNATE
import com.radiant.rpl.testa_kotlin.utils.Constant.REQUEST_TYPE
import kotlinx.coroutines.*
import java.io.*
import java.net.URL


 class ALertChooseFile(getImage: GetImage) : AppCompatActivity(),View.OnClickListener{

    /*lateinit var getImage:GetImage
    constructor(getImage: GetImage) : this() {
        this.getImage=getImage
    }*/

    private lateinit var binding: ActivityAlertChooseFileBinding
    //var getImage: GetImage = App.getImage!!
    var filename=""
    var filepath=""
    var launch_code=0

    var uploadFileKey=-1
    var file_type=0
    var request_type=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertChooseFileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.title_activity_alert_choose_file)
        file_type=intent.getIntExtra(FILE_TYPE,3)
        request_type=intent.getIntExtra(REQUEST_TYPE,0)
        binding.gallary.setOnClickListener(this)
        binding.camera.setOnClickListener(this)
        binding.file.setOnClickListener(this)
        binding.cancel.setOnClickListener(this)

        if (file_type==1)
        {
            binding.file.visibility=View.GONE
        }
        else if (file_type==2)
        {
            binding.camera.visibility=View.GONE
        }
        else
        {
            binding.file.visibility=View.VISIBLE
        }


    }



    override fun onClick(v: View?) {
        if (v?.id== R.id.gallary)
        {
            openGallery()
        }
       else if(v?.id== R.id.camera)
        {
            openCamera()
        }
        else if(v?.id== R.id.file)
        {
            openGallery()
        }
        else if(v?.id== R.id.cancel)
        {
            finish()
        }
    }

    private fun openGallery() {

            launch_code=PICK_IMAGE_ALTERNATE
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            resultLauncher.launch(Intent.createChooser(gallery, getString(R.string.selectpitchers)))

    }

    private fun openFile() {
        launch_code=PICK_IMAGE_ALTERNATE
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        val ACCEPT_MIME_TYPES = arrayOf(
            "application/pdf",
            "image/*"
        )
        intent.putExtra(Intent.EXTRA_MIME_TYPES, ACCEPT_MIME_TYPES);
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        resultLauncher.launch(Intent.createChooser(gallery,  getString(R.string.selectpitchers)))

    }

    private fun openCamera() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA),
                       MY_CAMERA_PERMISSION_CODE
                    )
                } else {

                        launch_code=CAMERA_REQUEST
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        cameraIntent.putExtra(
                            "android.intent.extras.CAMERA_FACING",
                            Camera.CameraInfo.CAMERA_FACING_BACK
                        )
                        cameraIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1)
                        cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)
                        resultLauncher.launch(cameraIntent)

                }
            } else {



                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_FRONT)
                cameraIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1)
                cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)
                resultLauncher.launch(cameraIntent)//CAMERA_REQUEST
                launch_code=CAMERA_REQUEST


            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->


        if(result.resultCode== RESULT_OK)
        {
            val data: Intent? = result.data

            if (launch_code== CAMERA_REQUEST)
            {
                if (request_type==1)
                {
                    val photo = data?.extras!!["data"] as Bitmap?
                    val intent=Intent()
                    intent.putExtra("data",photo)
                    setResult(CAMERA_REQUEST,intent)
                    finish()
                }
                else if(request_type==2)
                {
                    val photo = data!!.extras!!["data"] as Bitmap?
                    getImage.aadharFront(photo!!)
                    finish()
                }
                else if(request_type==3)
                {
                    val photo = data!!.extras!!["data"] as Bitmap?
                    getImage.aadharBack(photo!!)
                    finish()
                }

            }

            else if (launch_code== PICK_IMAGE_ALTERNATE )
            {
                    val data: Intent? = result?.data
                    val fUri = data?.data
                    val fUtils = FileUtilsNew(this@ALertChooseFile)
                    val f = File(fUtils.getPath(fUri!!))
                    val url = f.absolutePath

                  val imgFile = File(url)

                val result: Deferred<Bitmap?> = GlobalScope.async {
                     BitmapFactory.decodeFile(imgFile.absolutePath)

                }


                GlobalScope.launch(Dispatchers.Main) {
                    // show bitmap on image view when available
                    //images.setImageBitmap(result.await())
                    binding.progress.visibility=View.VISIBLE
                    binding.loading.visibility=View.VISIBLE

                    result.await()?.let {
                        val out = ByteArrayOutputStream()
                        it.compress(Bitmap.CompressFormat.PNG, 60, out)
                       // val decoded = BitmapFactory.decodeStream(ByteArrayInputStream(out.toByteArray()))
                        if (request_type==1)
                        {
                            getImage.picimage(it)
                        }
                        else if (request_type==2)
                        {
                            getImage.aadharFront(it)
                        }
                        else if (request_type==3)
                        {
                            getImage.aadharBack(it)
                        }
                        else if (request_type==4)
                        {
                            getImage.picimage(it)
                        }
                        binding.progress.visibility=View.GONE
                        binding.loading.visibility=View.GONE

                        finish()
                    }

                }




            }
        }


    }
    fun getImageUri(inContext: Context, inImage: Bitmap?): Uri? {
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun getRealPathFromURI1(contentUri: Uri): String? {
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = managedQuery(contentUri, proj, null, null, null)
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } catch (e: java.lang.Exception) {
            contentUri.path
        }
    }


    @Throws(FileNotFoundException::class, IOException::class)
    fun getThumbnail(uri: Uri?): Bitmap? {
        var input: InputStream? = this.contentResolver.openInputStream(uri!!)
        val onlyBoundsOptions = BitmapFactory.Options()
        onlyBoundsOptions.inJustDecodeBounds = true
        onlyBoundsOptions.inDither = true //optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888 //optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
        input?.close()
        if (onlyBoundsOptions.outWidth == -1 || onlyBoundsOptions.outHeight == -1) {
            return null
        }
        val originalSize =
            if (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) onlyBoundsOptions.outHeight else onlyBoundsOptions.outWidth
        //val ratio = if (originalSize > THUMBNAIL_SIZE) originalSize / THUMBNAIL_SIZE else 1.0
        val bitmapOptions = BitmapFactory.Options()
       // bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio)
        bitmapOptions.inDither = true //optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888 //
        input = this.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
        input?.close()
        return bitmap
    }

    private fun getPowerOfTwoForSampleRatio(ratio: Double): Int {
        val k = Integer.highestOneBit(Math.floor(ratio).toInt())
        return if (k == 0) 1 else k
    }

    interface GetImage
    {
        fun  picimage(bitmap: Bitmap)
        fun  aadharFront(bitmap: Bitmap)
        fun  aadharBack(bitmap: Bitmap)
    }



    fun URL.toBitmap(): Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e: IOException){
            null
        }
    }



}