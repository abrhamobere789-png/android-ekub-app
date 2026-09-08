package com.ekub.app.di

import android.content.Context
import androidx.room.Room
import com.ekub.app.data.local.database.EkubDatabase
import com.ekub.app.data.local.dao.UserDao
import com.ekub.app.data.local.dao.EkubGroupDao
import com.ekub.app.data.local.dao.ContributionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideEkubDatabase(
        @ApplicationContext context: Context
    ): EkubDatabase {
        return Room.databaseBuilder(
            context,
            EkubDatabase::class.java,
            "ekub_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: EkubDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideGroupDao(database: EkubDatabase): EkubGroupDao {
        return database.groupDao()
    }

    @Singleton
    @Provides
    fun provideContributionDao(database: EkubDatabase): ContributionDao {
        return database.contributionDao()
    }
}
