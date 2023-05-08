package com.ejqe.mnlapp.members.presentation.list_members

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejqe.mnlapp.members.domain.GetInitialMembersUseCase
import com.ejqe.mnlapp.members.domain.ToggleMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val getInitialMembersUseCase: GetInitialMembersUseCase,
    private val toggleMemberUseCase: ToggleMemberUseCase
): ViewModel() {

    private val _state = mutableStateOf(MembersScreenState(members = listOf()))
    val state: State<MembersScreenState> get() = _state

    init { getMemberList() }

    private fun getMemberList() {
        viewModelScope.launch(Dispatchers.Main) {
            val members = getInitialMembersUseCase()
           _state.value = _state.value.copy(members = members)
        }
    }

    fun toggleOshiFavorite(name: String, OldValue: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            val updatedMembers = toggleMemberUseCase(name, OldValue)
            _state.value = _state.value.copy(members = updatedMembers)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}