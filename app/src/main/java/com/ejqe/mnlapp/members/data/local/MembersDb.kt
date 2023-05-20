package com.ejqe.mnlapp.members.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalMember::class], version = 3, exportSchema = false)
abstract class MembersDb: RoomDatabase() {
    abstract val dao: MembersDao
}