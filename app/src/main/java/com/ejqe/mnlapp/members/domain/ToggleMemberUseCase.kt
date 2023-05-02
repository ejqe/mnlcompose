package com.ejqe.mnlapp.members.domain

import com.ejqe.mnlapp.members.data.MembersRepository
import javax.inject.Inject

class ToggleMemberUseCase @Inject constructor(
    private val repository: MembersRepository
) {
    suspend operator fun invoke(name: String, oldValue: Boolean): List<Members> {
        val newValue = !oldValue
        repository.toggleOshi(name = name, value = newValue)
        return repository.getRemoteMembers()
    }
}
