package com.ekub.app.data.repository

import com.ekub.app.data.local.dao.EkubGroupDao
import com.ekub.app.data.local.entity.EkubGroupEntity
import com.ekub.app.domain.model.EkubGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EkubGroupRepository @Inject constructor(
    private val groupDao: EkubGroupDao
) {
    suspend fun saveGroup(group: EkubGroup) {
        groupDao.insertGroup(group.toEntity())
    }

    suspend fun saveGroups(groups: List<EkubGroup>) {
        groupDao.insertGroups(groups.map { it.toEntity() })
    }

    suspend fun updateGroup(group: EkubGroup) {
        groupDao.updateGroup(group.toEntity())
    }

    suspend fun deleteGroup(groupId: String) {
        // Implement delete logic
    }

    fun getGroupById(groupId: String): Flow<EkubGroup?> {
        return groupDao.getGroupById(groupId).map { it?.toDomain() }
    }

    fun getAllGroups(): Flow<List<EkubGroup>> {
        return groupDao.getAllGroups().map { groups ->
            groups.map { it.toDomain() }
        }
    }

    fun getActiveGroups(): Flow<List<EkubGroup>> {
        return groupDao.getGroupsByStatus("Active").map { groups ->
            groups.map { it.toDomain() }
        }
    }

    suspend fun clearAllGroups() {
        groupDao.deleteAllGroups()
    }

    private fun EkubGroup.toEntity(): EkubGroupEntity {
        return EkubGroupEntity(
            id = id,
            name = name,
            description = description,
            createdBy = createdBy,
            totalMembers = totalMembers,
            totalSavings = totalSavings,
            currency = currency,
            payoutFrequency = payoutFrequency,
            nextPayoutDate = nextPayoutDate,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun EkubGroupEntity.toDomain(): EkubGroup {
        return EkubGroup(
            id = id,
            name = name,
            description = description,
            createdBy = createdBy,
            totalMembers = totalMembers,
            totalSavings = totalSavings,
            currency = currency,
            payoutFrequency = payoutFrequency,
            nextPayoutDate = nextPayoutDate,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
