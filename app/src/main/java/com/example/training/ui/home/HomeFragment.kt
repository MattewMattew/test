package com.example.training.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import java.io.BufferedReader
import java.io.File

class HomeFragment : Fragment() {
    private lateinit var recyclerMovieList: RecyclerView
    var t: Int = 0
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_home, container, false)
        recyclerMovieList = view.findViewById(R.id.recyclerMovieList)
        recyclerMovieList.layoutManager = LinearLayoutManager(this.activity)
        val cache: String = activity?.cacheDir.toString()
        val fileName = "$cache/MovieJson.json"
        readJSONfromFile(fileName)


        return view
    }
    @SuppressLint("SetTextI18n", "InflateParams")
    fun listen(position: Int, post: MutableList<Movie>)
    {
        val list = mutableListOf<String>()
        val alertview = LayoutInflater.from(this.activity).inflate(R.layout.alert,null)
        val name = alertview.findViewById<TextView>(R.id.txt_name_alert_elem)
        val image = alertview.findViewById<ImageView>(R.id.image_movie_alert_elem)
        val amount = alertview.findViewById<TextView>(R.id.textView_alert)
        val add = alertview.findViewById<Button>(R.id.add)
        val remove = alertview.findViewById<Button>(R.id.remove)
        val addtocard = alertview.findViewById<Button>(R.id.addtocard)
        name.text = post[position].name
        Picasso.get().load(post[position].imageurl).into(image)
        amount.text = t.toString()
        add.setOnClickListener{
            t += 1
            amount.text = t.toString()
        }
        remove.setOnClickListener {
            if (t > 0) {
                t -= 1
            }
            amount.text = t.toString()

        }




        val customDialog = Dialog(requireActivity())
        customDialog.setContentView(alertview)
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog.show()
        customDialog.setOnDismissListener{
            t = 0
        }

        addtocard.setOnClickListener {
            val cache = activity?.cacheDir?.absolutePath
            val fileName = "$cache/Card.json"
            list.add(name.text.toString())
            list.add(amount.text.toString())
            post[position].imageurl?.let { it1 -> list.add(it1) }
            Log.d("TAG",list.toString()+fileName)
            writeJSONfromFile(fileName,list)
            list.clear()
            customDialog.dismiss()
        }

    }
    private fun writeJSONfromFile(s: String, movelist: MutableList<String>){
        val gson = Gson()
        val string : String = gson.toJson(movelist)
        val file = File(s)
        file.writeText(string)
    }

    private fun readJSONfromFile(f:String) {

        //Creating a new Gson object to read data
        var gson = GsonBuilder().create()
        //Read the PostJSON.json file
        val bufferedReader: BufferedReader = File(f).bufferedReader()
        // Read the text from bufferReader and store in String variable
        val inputString = bufferedReader.use { it.readText() }
        //Convert the Json File to Gson Object
        var post = gson.fromJson(inputString, Array<Movie>::class.java).toMutableList()
//        //Initialize the String Builder
        Log.d("TAG", "Done")
        recyclerMovieList.adapter= MyMovieAdapter(post, clickListener = {listen(it, post)})
    }
}