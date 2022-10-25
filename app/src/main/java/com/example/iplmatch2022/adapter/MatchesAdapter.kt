package com.example.iplmatch2022.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iplmatch2022.R
import com.example.iplmatch2022.model.TwoTeams
import de.hdodenhof.circleimageview.CircleImageView

class MatchesAdapter(val data: List<TwoTeams>, var listener:ClickListener) :
    RecyclerView.Adapter<MatchesAdapter.MyViewHolder>() {


    interface ClickListener{
        fun onClickOfItem(twoTeams:TwoTeams,position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fixture, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image_team1.setImageResource(data.get(position).team1.teamImage)
        holder.image_team2.setImageResource(data.get(position).team2.teamImage)
        val twoTeams = data.get(position)
        holder.ll_status.visibility = View.VISIBLE
        if (twoTeams.team1.status == 1 && twoTeams.team2.status == 0){
            holder.txt_team1_status.text = "WIN"
            holder.txt_team1_status.setTextColor(Color.GREEN)
            holder.txt_team2_status.text = "LOST"
            holder.txt_team2_status.setTextColor(Color.RED)
        }else if (twoTeams.team1.status == 0 && twoTeams.team2.status == 1){
            holder.txt_team1_status.text = "LOST"
            holder.txt_team1_status.setTextColor(Color.RED)
            holder.txt_team2_status.text = "WIN"
            holder.txt_team2_status.setTextColor(Color.GREEN)
        }else{
            holder.ll_status.visibility = View.GONE
        }

    }

    fun autoClick(position:Int){
        listener.onClickOfItem(data.get(position),position)
    }
    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image_team1: CircleImageView = itemView.findViewById(R.id.img_team1)
        val image_team2: CircleImageView = itemView.findViewById(R.id.img_team2)
        val txt_team1_status: TextView = itemView.findViewById(R.id.txt_team1_status)
        val txt_team2_status: TextView = itemView.findViewById(R.id.txt_team2_status)
        val ll_status:LinearLayout = itemView.findViewById(R.id.ll_status)
    }
}