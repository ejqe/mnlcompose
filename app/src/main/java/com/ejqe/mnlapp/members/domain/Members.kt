package com.ejqe.mnlapp.members.domain

data class Members(
    val name: String,
    val imageUrl: String,
    val isOshi: Boolean = false
)
