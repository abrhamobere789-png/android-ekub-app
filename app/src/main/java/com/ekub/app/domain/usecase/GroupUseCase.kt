package com.ekub.app.domain.usecase

import com.ekub.app.data.repository.EkubGroupRepository
import com.ekub.app.domain.model.EkubGroup
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGroupsUseCase @Inject constructor(
    private val groupRepository: EkubGroupRepository
) {
    operator fun invoke(): Flow<List<EkubGroup>> {
        return groupRepository.getAllGroups()
    }
}

class GetGroupByIdUseCase @Inject constructor(
    private val groupRepository: EkubGroupRepository
) {
    operator fun invoke(groupId: String): Flow<EkubGroup?> {
        return groupRepository.getGroupById(groupId)
    }
}

class GetActiveGroupsUseCase @Inject constructor(
    private val groupRepository: EkubGroupRepository
) {
    operator fun invoke(): Flow<List<EkubGroup>> {
        return groupRepository.getActiveGroups()
    }
}

class SaveGroupUseCase @Inject constructor(
    private val groupRepository: EkubGroupRepository
) {
    suspend operator fun invoke(group: EkubGroup) {
        groupRepository.saveGroup(group)
    }
}
