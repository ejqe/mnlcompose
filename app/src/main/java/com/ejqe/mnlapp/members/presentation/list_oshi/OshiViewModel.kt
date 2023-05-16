package com.ejqe.mnlapp.members.presentation.list_oshi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejqe.mnlapp.members.data.repository.MemberRepositoryImpl
import com.ejqe.mnlapp.members.domain.model.Members
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OshiViewModel @Inject constructor(
    private val repositoryImpl: MemberRepositoryImpl
): ViewModel() {
    private val _state = mutableStateOf<List<Members>>(listOf())
    val state: State<List<Members>> get() = _state

    init { getOshiList() }

    private fun getOshiList() {
        viewModelScope.launch(Dispatchers.Main) {
            val domainMembers = repositoryImpl.getLocalMembers()
            _state.value = domainMembers

        }


    }







}