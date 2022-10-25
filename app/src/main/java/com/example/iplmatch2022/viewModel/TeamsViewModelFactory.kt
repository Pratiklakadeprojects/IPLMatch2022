package com.example.iplmatch2022.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iplmatch2022.repository.ListOfTeamRepository
import java.lang.IllegalArgumentException

 class TeamsViewModelFactory(private val repository: ListOfTeamRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsViewModel::class.java)){
            return TeamsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view Model class")
    }
}


