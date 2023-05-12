package com.ejqe.mnlapp.members.presentation.list_members

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejqe.mnlapp.members.domain.use_case.GetInitialMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val getInitialMembersUseCase: GetInitialMembersUseCase
): ViewModel() {

    private val _state = mutableStateOf(
        MembersScreenState(members = listOf(), isLoading = true)) //can be changed so it cannot be exposed outside
    val state: State<MembersScreenState> get() = _state //cannot change, exposed outside on Read Only

    private val errorHandler = CoroutineExceptionHandler{
            _, exception -> exception.printStackTrace()
            //set value of error and set loading to false
            _state.value = _state.value.copy(error = exception.message, isLoading = false)}

    init { getMemberList() }

    private fun getMemberList() {
        viewModelScope.launch(Dispatchers.Main) {
            val members = getInitialMembersUseCase()
           _state.value = _state.value.copy(members = members, isLoading = false)
        }
    }



}