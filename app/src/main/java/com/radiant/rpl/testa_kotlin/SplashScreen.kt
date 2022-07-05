package com.radiant.rpl.testa_kotlin

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.radiant.rpl.testa_kotlin.databinding.ActivitySplashScreenBinding
import java.util.*

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tim= Timer()
        tim.schedule(object : TimerTask() {
            override fun run() {
                /*  val ob= SesssionManager(this@Splash)
                  ob.checkLogin()
                  finish()
  */
                startActivity(Intent(this@SplashScreen,MainActivity::class.java))
            }
        }, 2000)
    }

}