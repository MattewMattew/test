package com.example.training

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var image: ImageView
    var t: Float = 0.toFloat()
    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_700)
        image = findViewById(R.id.imageView)
        image.alpha = 0.toFloat()
        if (isConnected)
        {
            val timer = object: CountDownTimer(2000, 20){
                override fun onTick(p0: Long) {
                    t += 0.02.toFloat()
                    image.alpha = t
                }

                override fun onFinish() {
                    val intent =  Intent(this@MainActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            timer.start()
        }
        else{
            val atert = AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Internet connection is lost!")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }

    }
}