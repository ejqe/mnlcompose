package com.ejqe.mnlapp.members.data.repository

import com.ejqe.mnlapp.members.data.local.MembersDao
import com.ejqe.mnlapp.members.data.local.OshiLocalMember
import com.ejqe.mnlapp.members.data.remote.MembersApiService
import com.ejqe.mnlapp.members.domain.model.Members
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private var restInterface: MembersApiService,
    private var membersDao: MembersDao
){


    //Get from Remote Load
    suspend fun getRemoteMembers(): List<Members> {
        withContext(Dispatchers.IO) {
            try {
            refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        if (membersDao.getAll().isEmpty())
                            throw Exception("Something went wrong." + "Check your Internet Connection")
                    }
                    else -> throw e
                }
            }
        }
        return getLocalMembers()
    }


    //Convert Remote to Local; returns nothing
    private suspend fun refreshCache() {
        val remoteMembers = restInterface.getMembers()
        val oshiMembers = membersDao.getAllOshied()

        //map everything from REMOTE + isOshi to LOCAL
        membersDao.addAll(remoteMembers.map { it.remoteToLocal() })

        //copy/map LOCAL name and isOshi to OSHILOCAL
        membersDao.updateAll(oshiMembers.map { it.localToOshiLocal() })
    }


    //Get from local
    suspend fun getLocalMembers(): List<Members> {
        return withContext(Dispatchers.IO) {
           membersDao.getAll().map{ it.localToDomain() }
        }
    }



    //Get Local Member
    suspend fun getLocalMember(name: String): Members {
        return withContext(Dispatchers.IO) {
            membersDao.get(name).localToDomain()

        }
    }


    //update OSHILOCAL name and isOshi to newValue; returns nothing
    suspend fun toggleOshi(name: String, value: Boolean) {
        return withContext(Dispatchers.IO) {
            membersDao.update(OshiLocalMember(name = name, isOshi = value))
        }
    }





}