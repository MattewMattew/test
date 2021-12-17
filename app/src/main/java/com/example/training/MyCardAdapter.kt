package com.example.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCardAdapter(private val movieList: MutableList<Card>):
    RecyclerView.Adapter<MyCardAdapter.MyCardViewHolder>() {
    class MyCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView? = itemView.findViewById(R.id.txt_name_alert_elem)
        val amount: TextView? = itemView.findViewById(R.id.txt_amount_elem)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyCardViewHolder, position: Int) {
        holder.name?.text = movieList[position].name
        holder.amount?.text = movieList[position].amount

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout,parent,false)
        return MyCardViewHolder(itemView)
    }
}