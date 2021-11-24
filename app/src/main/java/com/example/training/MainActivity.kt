package com.example.training

import android.animation.ObjectAnimator
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
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var image: ImageView
    lateinit var button: Button
    lateinit var progressBar: ProgressBar
    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.login)
        password = findViewById(R.id.password)
        image = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)

        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_800)

        image.alpha = 0.toFloat()
        email.alpha = 0.toFloat()
        password.alpha = 0.toFloat()
        button.alpha = 0.toFloat()
        if (isConnected)
        {
            val timer = object: CountDownTimer(2000, 20){
                override fun onTick(p0: Long) {
                }

                override fun onFinish() {
                    val objectAnimator3 = ObjectAnimator.ofFloat(image, "alpha", 0f,1f)
                    objectAnimator3.duration = 500
                    objectAnimator3.start()
                    val objectAnimatorbar = ObjectAnimator.ofFloat(progressBar, "alpha", 1f,0f)
                    objectAnimatorbar.duration = 1
                    objectAnimatorbar.start()
                    val timer = object: CountDownTimer(2000, 20){
                        override fun onTick(p0: Long) {
                        }

                        override fun onFinish() {
                            val objectAnimator = ObjectAnimator.ofFloat(image, "translationX", -330f)
                            objectAnimator.duration = 600
                            objectAnimator.start()
                            val objectAnimator2 = ObjectAnimator.ofFloat(image, "translationY", -760f)
                            objectAnimator2.duration = 600
                            objectAnimator2.start()
                            val objectAnimator4 = ObjectAnimator.ofFloat(image, "scaleX", 1f,0.45f)
                            objectAnimator4.duration = 600
                            objectAnimator4.start()
                            val objectAnimator5 = ObjectAnimator.ofFloat(image, "scaleY", 1f,0.45f)
                            objectAnimator5.duration = 600
                            objectAnimator5.start()
                            val timer = object: CountDownTimer(500, 20){
                                override fun onTick(p0: Long) {
                                }

                                override fun onFinish() {
                                    val objectAnimator6 = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f)
                                    objectAnimator6.duration = 600
                                    objectAnimator6.start()
                                    val objectAnimator7 = ObjectAnimator.ofFloat(email, "alpha", 0f, 1f)
                                    objectAnimator7.duration = 600
                                    objectAnimator7.start()
                                    val objectAnimator8 = ObjectAnimator.ofFloat(password, "alpha", 0f, 1f)
                                    objectAnimator8.duration = 600
                                    objectAnimator8.start()
                                }
                            }
                            timer.start()
                        }
                    }
                    timer.start()
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

    fun login(view: android.view.View) {
        if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
            if (email.text.toString() == "123" && password.text.toString() == "123"){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val alert = AlertDialog.Builder(this).setTitle("Error")
                    .setPositiveButton("Ok", null)
                    .setMessage("Your Login or Password is'n correct!")
                    .create()
                    .show()
            }
        }
        else{
            val alert = AlertDialog.Builder(this).setTitle("Error")
                .setPositiveButton("Ok", null)
                .setMessage("You miss Login or Password!")
                .create()
                .show()
        }
    }
}