package com.ejqe.mnlapp.members.presentation.details


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejqe.mnlapp.members.data.MembersRepository
import com.ejqe.mnlapp.members.domain.Members
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MembersRepository,
    private val stateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf<Members?>(null)
    val state: State<Members?> get() = _state

    init { getLocal() }

    private fun getLocal() {
        val name = stateHandle.get<String>("member_name") ?: ""
        viewModelScope.launch {
            val member = repository.getLocalMember(name)
            _state.value = member

        }
    }

}

