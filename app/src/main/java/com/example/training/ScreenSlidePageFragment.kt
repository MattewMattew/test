package com.example.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.BufferedReader
import java.io.File

class ScreenSlidePageFragment(private val position: Int) : Fragment() {

    private lateinit var name: TextView
    private lateinit var team: TextView
    private lateinit var createdby: TextView
    private lateinit var biography: TextView
    private lateinit var image: ImageView
    private lateinit var realName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        val list: MutableList<Movie>
        val fileName = activity?.cacheDir?.absolutePath+"/MovieJson.json"
        list = readJSONfromFileUpdate(fileName)
        name = view.findViewById(R.id.txt_namePager)
        team = view.findViewById(R.id.txt_teamPager)
        createdby = view.findViewById(R.id.txt_createdbyPager)
        biography = view.findViewById(R.id.txt_bioPager)
        image = view.findViewById(R.id.imagePager)
        realName = view.findViewById(R.id.txt_realnamePager)
        name.text = list[position].name
        team.text = list[position].team
        createdby.text = list[position].createdby
        biography.text = list[position].bio
        Picasso.get().load(list[position].imageurl).into(image)
        realName.text = list[position].realname
        return view
    }
    private fun readJSONfromFileUpdate(s : String): MutableList<Movie> {
        val gson = Gson()
        val bufferedReader: BufferedReader = File(s).bufferedReader()
        val input = bufferedReader.use {it.readText()}
        val read = gson.fromJson(input, Array<Movie>::class.java).toMutableList()
        return read
    }
}