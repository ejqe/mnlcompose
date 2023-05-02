package com.ejqe.mnlapp.members.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class OshiLocalMember(
    @ColumnInfo(name = "_name")
    val name: String,
    val isOshi: Boolean
)