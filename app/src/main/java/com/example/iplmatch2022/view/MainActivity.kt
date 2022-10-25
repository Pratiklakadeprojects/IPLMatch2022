package com.example.iplmatch2022.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.iplmatch2022.R
import com.example.iplmatch2022.adapter.AdapterForTeams
import com.example.iplmatch2022.databinding.ActivityMainBinding
import com.example.iplmatch2022.model.Team
import com.example.iplmatch2022.model.TwoTeams
import com.example.iplmatch2022.repository.ListOfTeamRepository
import com.example.iplmatch2022.viewModel.TeamsViewModel
import com.example.iplmatch2022.viewModel.TeamsViewModelFactory

import java.io.Serializable

class MainActivity  : AppCompatActivity(), AdapterForTeams.ClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var teamViewModel: TeamsViewModel
    lateinit var repository: ListOfTeamRepository
    lateinit var factory: TeamsViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        repository = ListOfTeamRepository()
        factory = TeamsViewModelFactory(repository)
        teamViewModel = ViewModelProvider(this,factory).get(TeamsViewModel::class.java)
        initRecyclerView()
        teamViewModel.getTeamList().observe(this, Observer {
            binding.rvTeams.adapter = AdapterForTeams(this,it,this)
        })
        binding.btnStart.setOnClickListener {
            val data:List<TwoTeams> = teamViewModel.getTeamFixture()
            val intent = Intent(this, DisplayMatchesActivity::class.java)
            intent.putExtra("teamList",data as Serializable)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.rvTeams.layoutManager = GridLayoutManager(this,2)
    }

    override fun onRowClick(team: Team) {

    }
}