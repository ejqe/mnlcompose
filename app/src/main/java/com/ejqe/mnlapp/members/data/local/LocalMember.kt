package com.ejqe.mnlapp.members.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ejqe.mnlapp.members.domain.Members
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Members")
data class LocalMember(
    @PrimaryKey()
    @ColumnInfo(name = "_name")
    @SerializedName("_name")
    val name: String,

    val imageUrl: String,

    val isOshi: Boolean = false

) {
    fun localToDomain(): Members {
        return Members(
            name = name,
            imageUrl = imageUrl,
            isOshi = isOshi
        )
    }

    fun localToOshiLocal(): OshiLocalMember {
        return OshiLocalMember(
           name = name,
           isOshi = isOshi
        )
    }
}
