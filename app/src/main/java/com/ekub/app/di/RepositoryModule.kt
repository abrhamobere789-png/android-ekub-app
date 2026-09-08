package com.ekub.app.di

import com.ekub.app.data.repository.ContributionRepository
import com.ekub.app.data.repository.EkubGroupRepository
import com.ekub.app.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userRepository: UserRepository
    ): UserRepository = userRepository

    @Singleton
    @Provides
    fun provideGroupRepository(
        groupRepository: EkubGroupRepository
    ): EkubGroupRepository = groupRepository

    @Singleton
    @Provides
    fun provideContributionRepository(
        contributionRepository: ContributionRepository
    ): ContributionRepository = contributionRepository
}
