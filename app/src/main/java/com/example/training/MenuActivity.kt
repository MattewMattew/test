package com.example.training

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.training.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.white_dark)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_menu)
        navView.setupWithNavController(navController)
    }




    fun next(view: android.view.View) {
        if (isConnected)
        {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
