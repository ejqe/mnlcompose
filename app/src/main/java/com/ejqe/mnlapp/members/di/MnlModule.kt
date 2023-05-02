package com.ejqe.mnlapp.members.di

import android.content.Context
import androidx.room.Room
import com.ejqe.mnlapp.members.data.local.MembersDao
import com.ejqe.mnlapp.members.data.local.MembersDb
import com.ejqe.mnlapp.members.data.remote.MembersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MnlModule {

    @Provides
    fun provideRoomDao(database: MembersDb): MembersDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): MembersDb {
        return Room.databaseBuilder(
            appContext,
            MembersDb::class.java,
            "members_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fan-club-app-9ebd8-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .build()
    }

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit): MembersApiService {
        return retrofit.create(MembersApiService::class.java)
    }


}