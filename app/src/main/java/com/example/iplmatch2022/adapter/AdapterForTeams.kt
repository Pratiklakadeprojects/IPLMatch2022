package com.example.iplmatch2022.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iplmatch2022.R
import com.example.iplmatch2022.model.Team

class AdapterForTeams(val context:Context, val data:List<Team>, listener:ClickListener): RecyclerView.Adapter<AdapterForTeams.MyViewHolder>() {
    var listener:ClickListener = listener
    interface ClickListener{
        fun onRowClick(team: Team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item,parent,false)
        return MyViewHolder(view,listener,data)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(data.get(position).teamImage)
        holder.text.setText(data.get(position).teamName)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(itemView: View,listener:ClickListener,data: List<Team>) : RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.profile_image)
        val text:TextView = itemView.findViewById(R.id.txt_name)
        val view: Unit = itemView.rootView.setOnClickListener {
            listener.onRowClick(data.get(adapterPosition))
        }
    }
}