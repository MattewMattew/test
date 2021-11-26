package com.example.training

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    lateinit var recyclerMovieList: RecyclerView
    lateinit var adapter: MyMovieAdapter
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_menu)
        navView.setupWithNavController(navController)
        recyclerMovieList = findViewById(R.id.recyclerMovieList)
        mService = Common.retrofitService
        recyclerMovieList.layoutManager = LinearLayoutManager(this)
        mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

            }
            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                recyclerMovieList.adapter= MyMovieAdapter(baseContext,response.body() as MutableList<Movie>)

            }
        })
    }




}