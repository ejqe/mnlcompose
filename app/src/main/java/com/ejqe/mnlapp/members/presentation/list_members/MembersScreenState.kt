package com.ejqe.mnlapp.members.presentation.list_members

import com.ejqe.mnlapp.members.domain.model.Members

data class MembersScreenState(
    val members: List<Members>,
    val isLoading: Boolean,

    val error: String? = null,
    val searchText: String = "",

    val tabIndex: Int = 0,
    val tabMembers: List<Members>

)
data class ChipItem(
    val text: String,
    val isSelected: Boolean = false)
