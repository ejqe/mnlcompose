package com.ejqe.mnlapp.members.domain

import com.ejqe.mnlapp.members.data.MembersRepository
import javax.inject.Inject

class GetMemberUseCase @Inject constructor(
    private val repository: MembersRepository
) {
//    suspend operator fun invoke(): Members {
//        repository.
//    }
}