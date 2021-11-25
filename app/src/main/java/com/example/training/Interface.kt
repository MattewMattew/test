package com.example.training

import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.POST

interface Interface {
    @POST("/user/login")
    fun getAuth(@Body hashMap: HashMap<String,String>): Call<login>
}