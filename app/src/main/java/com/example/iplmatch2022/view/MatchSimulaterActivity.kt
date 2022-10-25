package com.example.iplmatch2022.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.iplmatch2022.R
import com.example.iplmatch2022.databinding.ActivitySimulateBinding
import com.example.iplmatch2022.model.TwoTeams
import java.io.Serializable
import java.util.*

class MatchSimulaterActivity : AppCompatActivity() {
    lateinit var twoTeams: TwoTeams
    private val data = listOf(1, 2)
    lateinit var binding: ActivitySimulateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simulate)
        twoTeams = intent.extras?.get("team") as TwoTeams
        binding.imgTeam1.setImageResource(twoTeams.team1.teamImage)
        binding.imgTeam2.setImageResource(twoTeams.team2.teamImage)
        startMatch()
    }

    private fun startMatch() {
        Toast.makeText(SimulateActivity@ this, "Match starting", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ //Write your code here
            showToast("Match is Live ")
        }, 2000)
        val value = getRandomElement(data)
        val message: String
        if (value == 1) {
            message = twoTeams.team1.teamName + " Win"
            twoTeams.team1.status = 1
        } else {
            message = twoTeams.team2.teamName + " Win"
            twoTeams.team2.status = 1
        }
        Handler(Looper.getMainLooper()).postDelayed({
            showToast(message)
            goToPreviousActivity();
        }, 5000)
    }

    private fun goToPreviousActivity() {
        val i = Intent()
        i.putExtra("teamWithWinningStatus", twoTeams as Serializable)
        setResult(RESULT_OK, i)
        finish()
    }

    private fun getRandomElement(list: List<Int>): Int {
        val random = Random()
        return list[random.nextInt(list.size)]
    }

    private fun showToast(toast: String?) {
        runOnUiThread {
            Toast.makeText(
                this@MatchSimulaterActivity,
                toast,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}