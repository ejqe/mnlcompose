package com.ejqe.mnlapp.members.domain.use_case

import com.ejqe.mnlapp.members.data.repository.MemberRepositoryImpl
import com.ejqe.mnlapp.members.domain.model.Members
import javax.inject.Inject

class GetInitialMembersUseCase @Inject constructor(
    private val repository: MemberRepositoryImpl
) {
    suspend operator fun invoke(): List<Members> {
        return repository.getRemoteMembers()
    }
}