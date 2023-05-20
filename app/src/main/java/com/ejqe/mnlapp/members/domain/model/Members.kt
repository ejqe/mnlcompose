package com.ejqe.mnlapp.members.domain.model

data class Members(
    val name: String,
    val imageUrl: String,
    val isOshi: Boolean = false,
    val generation: String
)
