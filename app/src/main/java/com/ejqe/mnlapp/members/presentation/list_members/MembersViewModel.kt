package com.ejqe.mnlapp.members.presentation.list_members

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejqe.mnlapp.members.data.repository.MemberRepositoryImpl
import com.ejqe.mnlapp.members.domain.use_case.GetInitialMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembersViewModel @Inject constructor(
    private val getInitialMembersUseCase: GetInitialMembersUseCase,
    private val repositoryImpl: MemberRepositoryImpl
) : ViewModel() {

    private val _state = mutableStateOf(
        MembersScreenState(members = listOf(), isLoading = true, tabMembers = listOf())
    ) //can be changed so it cannot be exposed outside
    val state: State<MembersScreenState> get() = _state //cannot change, exposed outside on Read Only

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        //set value of error and set loading to false
        _state.value = _state.value.copy(error = exception.message, isLoading = false)
    }

    private var searchJob: Job? = null

    init {
        getMemberList()
    }

    private fun getMemberList() {
        viewModelScope.launch(Dispatchers.Main + errorHandler) {
            val members = getInitialMembersUseCase()
            _state.value = _state.value.copy(members = members, isLoading = false)
        }
    }


    fun onActionTextInput(newText: String) {
        _state.value = _state.value.copy(searchText = newText)
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            searchMembersInList(query = newText)
        }
    }


    private fun searchMembersInList(query: String) {

        val tabMemberList = state.value.tabMembers //should be fixed per tab

        val newMembersList = tabMemberList.filter {
            it.name.contains(query, ignoreCase = true)
        }
        _state.value = _state.value.copy(members = newMembersList)

    }

    suspend fun setTab(newTabIndex: Int) {
        _state.value = _state.value.copy(tabIndex = newTabIndex)

        _state.value = _state.value.copy(
            tabMembers = when (_state.value.tabIndex) {
                0 -> repositoryImpl.getLocalMembers()
                1 -> repositoryImpl.filterLocal("First")
                2 -> repositoryImpl.filterLocal("Second")
                3 -> repositoryImpl.filterLocal("Third")
                else -> listOf()
            }
        )
        searchMembersInList(query = state.value.searchText)

    }


}
