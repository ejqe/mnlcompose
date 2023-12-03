package com.ejqe.mnlapp.members.domain.repository

import com.ejqe.mnlapp.members.domain.model.Members

interface MemberRepository {

    suspend fun getRemoteMembers(): List<Members>
    suspend fun getLocalMembers(): List<Members>
    suspend fun getLocalMember(name: String): Members
    suspend fun toggleOshi(name: String, value: Boolean)
}