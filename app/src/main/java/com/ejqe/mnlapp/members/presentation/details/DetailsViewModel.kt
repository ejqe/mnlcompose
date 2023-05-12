package com.ejqe.mnlapp.members.presentation.details


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejqe.mnlapp.members.data.repository.MemberRepositoryImpl
import com.ejqe.mnlapp.members.domain.model.Members
import com.ejqe.mnlapp.members.domain.use_case.ToggleMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val toggleMemberUseCase: ToggleMemberUseCase,
    private val repository: MemberRepositoryImpl,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf<Members?>(null)
    val state: State<Members?> get() = _state

    init { getLocalMember() }

    private fun getLocalMember() {
        val memberName = stateHandle.get<String>("member_name") ?: ""
        viewModelScope.launch {
            val member = repository.getLocalMember(memberName)
            _state.value = member
        }
    }

    fun toggleOshiFavorite(name: String, OldValue: Boolean) {
        val memberName = stateHandle.get<String>("member_name") ?: ""
        viewModelScope.launch(Dispatchers.Main) {
            val updatedMembers = toggleMemberUseCase(name, OldValue)
            val selectedMember = updatedMembers.find { it.name == memberName }
            _state.value = selectedMember
        }
    }


}

