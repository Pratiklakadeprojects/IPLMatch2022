package com.example.iplmatch2022.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iplmatch2022.model.Team
import com.example.iplmatch2022.model.TwoTeams


class FixtureViewModel : ViewModel() {
    val data: MutableLiveData<List<TwoTeams>> = MutableLiveData()
    val newRoundValue: MutableLiveData<List<TwoTeams>> = MutableLiveData()
    val buttonClicked:MutableLiveData<Boolean> = MutableLiveData()
    val finalMatch:MutableLiveData<Boolean> = MutableLiveData()
    val position:MutableLiveData<Int> = MutableLiveData()

    init {
        position.value = 0
    }

    fun getTeams(): MutableLiveData<List<TwoTeams>> {
        return data
    }

    fun getNewRoundData(): MutableLiveData<List<TwoTeams>> {
        return newRoundValue
    }

    fun removeDuplicates(list: MutableList<TwoTeams>): MutableList<Team> {
        val newList = mutableListOf<Team>()

        for (element in list) {
            val team1 = Team(element.team1.teamImage, element.team1.teamName, element.team1.status)
            val team2 = Team(element.team2.teamImage, element.team2.teamName, element.team2.status)
            if (team1.status == 1) {
                team1.status = 0
                newList.add(team1)
            } else {
                team2.status = 0
                newList.add(team2)
            }
        }


        val distinct = newList.distinctBy {
            it.teamName
        }

        return distinct as MutableList<Team>
    }

    fun getTeamFixture(teamList: MutableList<Team>): MutableList<TwoTeams> {
        val twoTeamList = mutableListOf<TwoTeams>()
        var temp = 1
        for (i in 0 until teamList.size / 2) {
            twoTeamList.add(TwoTeams(teamList.get(temp - 1), teamList.get(temp)))
            temp += 2
        }
        return twoTeamList
    }
}