package com.ejqe.mnlapp.members.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


class MainViewModel: ViewModel() {
    val screenTitle = mutableStateOf("")


    fun setTitle(newTitle: String) {
        screenTitle.value = newTitle
    }
}
