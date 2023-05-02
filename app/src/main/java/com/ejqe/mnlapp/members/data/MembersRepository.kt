package com.ejqe.mnlapp.members.data

import com.ejqe.mnlapp.members.data.local.LocalMember
import com.ejqe.mnlapp.members.data.local.MembersDao
import com.ejqe.mnlapp.members.data.local.OshiLocalMember
import com.ejqe.mnlapp.members.data.remote.MembersApiService
import com.ejqe.mnlapp.members.domain.Members
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class MembersRepository @Inject constructor(
    private var restInterface: MembersApiService,
    private var membersDao: MembersDao
){
    suspend fun toggleOshi(name: String, value: Boolean) {
        return withContext(Dispatchers.IO) {
            membersDao.update(OshiLocalMember(name = name, isOshi = value))
        }
    }

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

    //Get from local
    suspend fun getLocalMembers(): List<Members> {
        return withContext(Dispatchers.IO) {
           membersDao.getAll().map{//Map from LocalMember to Members(Domain)
               Members(it.name, it.imageUrl, it.isOshi)
           }
        }
    }

    //Convert Remote to Local
    private suspend fun refreshCache() {
        val remoteMembers = restInterface.getMembers()
        val oshiMembers = membersDao.getAllOshied()

        membersDao.addAll(remoteMembers.map {
            LocalMember(it.name, it.imageUrl, isOshi = false)
        })

        membersDao.updateAll(oshiMembers.map {
            OshiLocalMember(it.name, isOshi = true)
        })
    }

}