package com.ekub.app.di

import com.ekub.app.data.repository.ContributionRepository
import com.ekub.app.data.repository.EkubGroupRepository
import com.ekub.app.data.repository.UserRepository
import com.ekub.app.domain.usecase.GetAllGroupsUseCase
import com.ekub.app.domain.usecase.GetGroupByIdUseCase
import com.ekub.app.domain.usecase.GetActiveGroupsUseCase
import com.ekub.app.domain.usecase.SaveGroupUseCase
import com.ekub.app.domain.usecase.GetCurrentUserUseCase
import com.ekub.app.domain.usecase.GetUserByIdUseCase
import com.ekub.app.domain.usecase.SaveUserUseCase
import com.ekub.app.domain.usecase.LogoutUseCase
import com.ekub.app.domain.usecase.GetContributionsByGroupIdUseCase
import com.ekub.app.domain.usecase.GetContributionsByMemberIdUseCase
import com.ekub.app.domain.usecase.SaveContributionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllGroupsUseCase(
        repository: EkubGroupRepository
    ): GetAllGroupsUseCase = GetAllGroupsUseCase(repository)

    @Singleton
    @Provides
    fun provideGetGroupByIdUseCase(
        repository: EkubGroupRepository
    ): GetGroupByIdUseCase = GetGroupByIdUseCase(repository)

    @Singleton
    @Provides
    fun provideGetActiveGroupsUseCase(
        repository: EkubGroupRepository
    ): GetActiveGroupsUseCase = GetActiveGroupsUseCase(repository)

    @Singleton
    @Provides
    fun provideSaveGroupUseCase(
        repository: EkubGroupRepository
    ): SaveGroupUseCase = SaveGroupUseCase(repository)

    @Singleton
    @Provides
    fun provideGetCurrentUserUseCase(
        repository: UserRepository
    ): GetCurrentUserUseCase = GetCurrentUserUseCase(repository)

    @Singleton
    @Provides
    fun provideGetUserByIdUseCase(
        repository: UserRepository
    ): GetUserByIdUseCase = GetUserByIdUseCase(repository)

    @Singleton
    @Provides
    fun provideSaveUserUseCase(
        repository: UserRepository
    ): SaveUserUseCase = SaveUserUseCase(repository)

    @Singleton
    @Provides
    fun provideLogoutUseCase(
        repository: UserRepository
    ): LogoutUseCase = LogoutUseCase(repository)

    @Singleton
    @Provides
    fun provideGetContributionsByGroupIdUseCase(
        repository: ContributionRepository
    ): GetContributionsByGroupIdUseCase = GetContributionsByGroupIdUseCase(repository)

    @Singleton
    @Provides
    fun provideGetContributionsByMemberIdUseCase(
        repository: ContributionRepository
    ): GetContributionsByMemberIdUseCase = GetContributionsByMemberIdUseCase(repository)

    @Singleton
    @Provides
    fun provideSaveContributionUseCase(
        repository: ContributionRepository
    ): SaveContributionUseCase = SaveContributionUseCase(repository)
}
