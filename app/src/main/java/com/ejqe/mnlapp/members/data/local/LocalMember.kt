package com.ejqe.mnlapp.members.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ejqe.mnlapp.members.domain.model.Members
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Members")
data class LocalMember(
    @PrimaryKey()
    @ColumnInfo(name = "_name")
    @SerializedName("_name")
    val name: String,

    val imageUrl: String,

    val isOshi: Boolean = false,

    val generation: String

) {
    fun localToDomain(): Members {
        return Members(
            name = name,
            imageUrl = imageUrl,
            isOshi = isOshi,
            generation = generation

        )
    }

    fun localToOshiLocal(): OshiLocalMember {
        return OshiLocalMember(
           name = name,
           isOshi = isOshi,
        )
    }
}
