package com.ejqe.mnlapp.members.domain

import com.ejqe.mnlapp.members.data.MembersRepository
import javax.inject.Inject

class ToggleMemberUseCase @Inject constructor(
    private val repository: MembersRepository
) {

    //1 get remote member
    //2 cache to local
    //3 change isOshi value
    //4 return the new toggled value
    suspend operator fun invoke(name: String, oldValue: Boolean): List<Members> {
        val newValue = !oldValue
        repository.toggleOshi(name = name, value = newValue)
        return repository.getLocalMembers()
    }
}
