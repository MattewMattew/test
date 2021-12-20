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
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.util.*

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
        val list = JSONArray()
        val obj = JSONObject()
        val alertview = LayoutInflater.from(this.activity).inflate(R.layout.alert,null)
        val name = alertview.findViewById<TextView>(R.id.txt_name_alert_elem)
        val image = alertview.findViewById<ImageView>(R.id.image_movie_alert_elem)
        val amount = alertview.findViewById<TextView>(R.id.textView_alert)
        val add = alertview.findViewById<Button>(R.id.add)
        val remove = alertview.findViewById<Button>(R.id.remove)
        val addtocard = alertview.findViewById<Button>(R.id.addtocard)
        name.text = post[position].name
        amount.text = t.toString()
        Picasso.get().load(post[position].imageurl).into(image)
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
            Log.d("TAG", "")
            val cache = activity?.cacheDir?.absolutePath
            val fileName = "$cache/Card.json"
            if ("0"  !in amount.text)
            {
            obj.put("name",name.text)
            obj.put("amount",amount.text)
            obj.put("url",post[position].imageurl)
            writeJSONfromFile(fileName,obj)
            customDialog.dismiss()
            }
        }

    }
    private fun writeJSONfromFile(s: String, movelist: JSONObject){
        val file = File(s)
        Log.d("TAG", "")
        if (File(s).canRead())
        {
            val card : MutableList<Card> = readJSONfromFileUpdate(s)
            Log.d("TAG", "Card size = "+card.size.toString())
            val obj = JSONObject()
            val list = mutableListOf<String>()
            for (i in 0..card.size-1) {
                card[i].name?.let { obj.put("name", it) }
                card[i].amount?.let { obj.put("amount", it) }
                card[i].url?.let { obj.put("url", it) }
                list.add(obj.toString())
            }
            list.add(movelist.toString())
            Log.d("TAG",list.toString())
            file.writeText(list.toString())
        }
        else
        {
            val list = mutableListOf<String>()
            list.add(movelist.toString())
            file.writeText(list.toString())
        }



    }
    private fun readJSONfromFileUpdate(s : String): MutableList<Card> {
        Log.d("TAG","1")
        var gson = Gson()
        Log.d("TAG","2 ${File(s).canRead()}")
        val bufferedReader: BufferedReader = File(s).bufferedReader()
        Log.d("TAG","3")
        var input = bufferedReader.use {it.readText()}
        Log.d("TAG", "4 $input")
        val read = gson.fromJson(input, Array<Card>::class.java).toMutableList()
        Log.d("TAG", "Done")
        return read
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
//        //Initialize the String Builde
        recyclerMovieList.adapter= MyMovieAdapter(post, clickListener = {listen(it, post)})
    }
}