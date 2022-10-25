package com.example.iplmatch2022.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.iplmatch2022.R
import com.example.iplmatch2022.databinding.ActivityLeaderBoardBinding
import com.example.iplmatch2022.model.TwoTeams
import java.util.*

class LeaderBoardActivity : AppCompatActivity() {
    lateinit var teams: TwoTeams
    lateinit var binding: ActivityLeaderBoardBinding
    private val data = listOf(1, 2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_leader_board)
        teams = intent.extras?.get("team") as TwoTeams
        val winner = getRandomElement(data)
        if (winner == 1) {
            binding.imgTeam1.setImageResource(teams.team1.teamImage)
            binding.imgTeam2.setImageResource(teams.team2.teamImage)
        } else {
            binding.imgTeam1.setImageResource(teams.team2.teamImage)
            binding.imgTeam2.setImageResource(teams.team1.teamImage)
        }
        binding.btnRestart.setOnClickListener {
            val i = Intent(LeaderBoardActivity@this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
    }
    private fun getRandomElement(list: List<Int>): Int {
        val random = Random()
        return list[random.nextInt(list.size)]
    }
}