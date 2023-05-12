package com.ejqe.mnlapp.members.presentation.list_members

import com.ejqe.mnlapp.members.domain.model.Members

data class MembersScreenState(
    val members: List<Members>,
    val isLoading: Boolean,
    val error: String? = null
)
