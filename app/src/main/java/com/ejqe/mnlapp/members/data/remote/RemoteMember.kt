package com.ejqe.mnlapp.members.data.remote

import com.ejqe.mnlapp.members.data.local.LocalMember
import com.google.gson.annotations.SerializedName

data class RemoteMember(
    @SerializedName("_name")
    val name: String,
    val imageUrl: String,
    val birthdate: String,
    val birthplace: String,
    val bloodType: String,
    val fullName: String,
    val generation: String,
    val height: String
) {
    fun remoteToLocal(): LocalMember {
        return LocalMember(
            name = name,
            imageUrl = imageUrl,
            isOshi = false
        )
    }
}
