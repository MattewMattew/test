package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler: RecyclerView = findViewById(R.id.recyclerView)
        recycler.layoutManager = GridLayoutManager(this, 3)
        recycler.adapter = Adapter(generate())
    }
    private fun generate(): MutableList<String> {
        val list = mutableListOf<String>()
        for (i in 0..100)
        {
            list.add("Some")
        }
        return list
    }
    class Adapter(private val vaules: List<String>): RecyclerView.Adapter<Adapter.ViewHolder>(){
        override fun getItemCount() = vaules.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text?.text = vaules[position]
        }


        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var text: TextView? = null
            init {
                text = itemView.findViewById(R.id.textrecycler)
            }
        }
    }
}