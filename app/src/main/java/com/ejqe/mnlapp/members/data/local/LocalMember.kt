package com.ejqe.mnlapp.members.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Members")
data class LocalMember(
    @PrimaryKey()
    @ColumnInfo(name = "_name")
    @SerializedName("_name")
    val name: String,

    val imageUrl: String,

    val isOshi: Boolean = false

)
