package com.example.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCardAdapter(val moveList: MutableList<String>):
    RecyclerView.Adapter<MyCardAdapter.MyCardViewHolder>() {
    class MyCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView? = itemView.findViewById(R.id.txt_name_alert_elem)
    }

    override fun getItemCount() = moveList.size

    override fun onBindViewHolder(holder: MyCardAdapter.MyCardViewHolder, position: Int) {
        holder.name?.text = moveList[position]
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardAdapter.MyCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout,parent,false)
        return MyCardViewHolder(itemView)
    }
}