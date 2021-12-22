package com.example.training.ui.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.training.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File

class OtherFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_other,container, false)
        viewPager = view.findViewById(R.id.otherPager)
        val list: MutableList<Movie>
        val fileName = activity?.cacheDir?.absolutePath+"/MovieJson.json"
        list = readJSONfromFileUpdate(fileName)
        viewPager.adapter = this.activity?.let { ScreenSlidePagerAdapter(it, list.size) }
        return view
    }
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity,val size: Int) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = size

        override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment(position)
    }
    private fun readJSONfromFileUpdate(s : String): MutableList<Movie> {
        var gson = Gson()
        val bufferedReader: BufferedReader = File(s).bufferedReader()
        var input = bufferedReader.use {it.readText()}
        val read = gson.fromJson(input, Array<Movie>::class.java).toMutableList()
        return read
    }

}

