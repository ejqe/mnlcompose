package com.ejqe.mnlapp.members.presentation.list

import com.ejqe.mnlapp.members.domain.Members

data class MembersScreenState(
    val members: List<Members>,
//    val isLoading: Boolean,
    val error: String? = null
)
