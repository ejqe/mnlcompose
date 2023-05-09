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

    //1 get remote member
    //2 cache to local(room)
    //3 change isOshi value and save to OshiLocal Entity
    //4 return the new toggled value list
    //5 copy/save the list to screen state
    fun toggleOshiFavorite(name: String, OldValue: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            val updatedMembers = toggleMemberUseCase(name, OldValue)
            _state.value = _state.value.copy(members = updatedMembers)
        }
    }

}