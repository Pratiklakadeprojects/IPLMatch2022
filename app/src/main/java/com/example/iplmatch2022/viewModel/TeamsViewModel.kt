package com.example.iplmatch2022.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iplmatch2022.model.Team
import com.example.iplmatch2022.model.TwoTeams
import com.example.iplmatch2022.repository.ListOfTeamRepository


class TeamsViewModel(val repository: ListOfTeamRepository): ViewModel() {
    var data = MutableLiveData<List<Team>>()

    fun getTeamList(): MutableLiveData<List<Team>> {
        data = repository.getList()
        return data
    }

    fun getTeamFixture(): List<TwoTeams> {
        val twoTeamsList = ArrayList<TwoTeams>()
        val repository = ListOfTeamRepository()
        val teamList = repository.getData()
        var temp = 1
        for (i in 0 until teamList.size / 2) {
            twoTeamsList.add(TwoTeams(teamList.get(temp - 1), teamList.get(temp)))
            temp += 2
        }
        return twoTeamsList
    }


}