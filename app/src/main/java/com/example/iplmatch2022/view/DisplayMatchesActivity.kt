package com.example.iplmatch2022.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iplmatch2022.R
import com.example.iplmatch2022.adapter.MatchesAdapter
import com.example.iplmatch2022.databinding.ActivityFixtureBinding
import com.example.iplmatch2022.model.Team
import com.example.iplmatch2022.model.TwoTeams
import com.example.iplmatch2022.viewModel.FixtureViewModel


class DisplayMatchesActivity : AppCompatActivity(), MatchesAdapter.ClickListener {

    var twoTeamList: MutableList<TwoTeams> = mutableListOf()
    lateinit var binding: ActivityFixtureBinding
    lateinit var fixtureViewModel: FixtureViewModel
    lateinit var fixtureAdapter: MatchesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fixture)
        twoTeamList = (intent.getSerializableExtra("teamList") as List<TwoTeams>).toMutableList()
        fixtureViewModel = ViewModelProvider(this).get(FixtureViewModel::class.java)
        fixtureViewModel.data.postValue(twoTeamList)

        fixtureViewModel.getTeams().observe(this, Observer {
            if (it.size == 1) {
                binding.rvTeams.layoutManager = LinearLayoutManager(this)
                fixtureAdapter = MatchesAdapter(it, this)
                binding.rvTeams.adapter = fixtureAdapter
                fixtureViewModel.finalMatch.value = true
                binding.btnSimulate.text = "Simulate and End"
            } else {
                binding.rvTeams.layoutManager = LinearLayoutManager(this)
                fixtureAdapter = MatchesAdapter(it, this)
                binding.rvTeams.adapter = fixtureAdapter
                if (fixtureViewModel.buttonClicked.value == true) {
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            runOnUiThread {
                                fixtureAdapter.autoClick(fixtureViewModel.position.value!! + 1)
                            }
                        }, 3000
                    )
                }
            }
        })
        binding.btnSimulate.setOnClickListener {
            if (fixtureViewModel.finalMatch.value == true) {
                val i = Intent(FixtureActivity@ this, LeaderBoardActivity::class.java)
                i.putExtra("team", twoTeamList.get(0))
                startActivity(i)
            } else {
                fixtureViewModel.buttonClicked.value = true
                fixtureAdapter.autoClick(fixtureViewModel.position.value!!)
            }
        }
    }

    override fun onClickOfItem(twoTeams: TwoTeams, position: Int) {
        fixtureViewModel.position.value = position
        val intent = Intent(this, MatchSimulaterActivity::class.java)
        intent.putExtra("team", twoTeams)
        resultLauncher.launch(intent)
    }


    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val teams: TwoTeams = data?.getSerializableExtra("teamWithWinningStatus") as TwoTeams
            twoTeamList.set(fixtureViewModel.position.value!!, teams)
            if (fixtureViewModel.position.value!! + 1 == twoTeamList.size) {
                fixtureViewModel.position.value = -1
                val newList: MutableList<Team> = fixtureViewModel.removeDuplicates(twoTeamList)
                val newTeam: MutableList<TwoTeams> = fixtureViewModel.getTeamFixture(newList)
                twoTeamList = mutableListOf()
                twoTeamList.addAll(newTeam)
                fixtureViewModel.data.postValue(twoTeamList)
            } else {
                fixtureViewModel.data.postValue(twoTeamList)
            }
        }
    }


}