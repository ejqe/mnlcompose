package com.ejqe.mnlapp.members.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MembersApiService {
    @GET("Members.json")
    suspend fun getMembers(): List<RemoteMember>

    @GET("Members.json?orderBy=\"_name\"")
    suspend fun getMember( @Query("equalTo") name: String): Map<String, RemoteMember>
}