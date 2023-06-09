package com.ejqe.mnlapp.members.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface MembersDao {

    @Query("SELECT * FROM Members")
    fun getAll(): List<LocalMember>

    @Query("SELECT * FROM Members WHERE generation = :generation")
    fun filter(generation: String): List<LocalMember>

    @Query("SELECT * FROM Members WHERE _name = :name")
    fun get(name: String): LocalMember


    @Upsert
    suspend fun addAll(members: List<LocalMember>)

    @Update(entity = LocalMember::class)
    suspend fun update(oshiLocalMember: OshiLocalMember)

    @Update(entity = LocalMember::class)
    suspend fun updateAll(oshiLocalMember: List<OshiLocalMember>)

    @Query("SELECT * FROM Members WHERE isOshi = 1")
    suspend fun getAllOshied(): List<LocalMember>

}