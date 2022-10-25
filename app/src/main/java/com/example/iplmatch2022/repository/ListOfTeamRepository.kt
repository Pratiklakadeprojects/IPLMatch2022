package com.example.iplmatch2022.repository

import androidx.lifecycle.MutableLiveData
import com.example.iplmatch2022.R
import com.example.iplmatch2022.model.Team


class ListOfTeamRepository {
    var list:MutableList<Team> = arrayListOf()
    var mutableList = MutableLiveData<List<Team>>()

    fun getList():MutableLiveData<List<Team>>{
        list = getData()
        mutableList.postValue(list)
        return mutableList
    }


    fun getData(): MutableList<Team> {
        list = mutableListOf()

        list.add(Team(R.drawable.mumbai_logo,"Mumbai Indians",0))
        list.add(Team(R.drawable.punjab_logo,"Kings X! Punjab",0))
        list.add(Team(R.drawable.rr_logo,"Rajasthan Royals",0))
        list.add(Team(R.drawable.rcb_logo,"Royal Challengers Bangalore",0))
        list.add(Team(R.drawable.dc_logo,"Delhi Capitals",0))
        list.add(Team(R.drawable.srh_logo,"Sunrisers Hyderabad",0))
        list.add(Team(R.drawable.csk_logo,"Chennai Super Kings",0))
        list.add(Team(R.drawable.kkr_logo,"Kolkata Knight Riders",0))
        list.shuffle()
        return list
    }
}