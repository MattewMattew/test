package com.example.training

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import java.lang.reflect.Array.get

class SignInActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        email = findViewById(R.id.login)
        password = findViewById(R.id.password)
        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_700)
        if (isConnected)
        {
            print("Done")
        }
    }

    fun login(view: android.view.View) {
        if (email.text.toString().isNotEmpty()) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}