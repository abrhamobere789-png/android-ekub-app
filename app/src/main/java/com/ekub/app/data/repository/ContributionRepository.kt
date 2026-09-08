package com.ekub.app.data.repository

import com.ekub.app.data.local.dao.ContributionDao
import com.ekub.app.data.local.entity.ContributionEntity
import com.ekub.app.domain.model.Contribution
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContributionRepository @Inject constructor(
    private val contributionDao: ContributionDao
) {
    suspend fun saveContribution(contribution: Contribution) {
        contributionDao.insertContribution(contribution.toEntity())
    }

    suspend fun saveContributions(contributions: List<Contribution>) {
        contributionDao.insertContributions(contributions.map { it.toEntity() })
    }

    suspend fun updateContribution(contribution: Contribution) {
        contributionDao.updateContribution(contribution.toEntity())
    }

    fun getContributionById(contributionId: String): Flow<Contribution?> {
        return contributionDao.getContributionById(contributionId).map { it?.toDomain() }
    }

    fun getContributionsByGroupId(groupId: String): Flow<List<Contribution>> {
        return contributionDao.getContributionsByGroupId(groupId).map { contributions ->
            contributions.map { it.toDomain() }
        }
    }

    fun getContributionsByMemberId(memberId: String): Flow<List<Contribution>> {
        return contributionDao.getContributionsByMemberId(memberId).map { contributions ->
            contributions.map { it.toDomain() }
        }
    }

    fun getCompletedContributions(groupId: String): Flow<List<Contribution>> {
        return contributionDao.getContributionsByStatus("Completed").map { contributions ->
            contributions.filter { it.groupId == groupId }.map { it.toDomain() }
        }
    }

    suspend fun deleteContributionsByGroupId(groupId: String) {
        contributionDao.deleteContributionsByGroupId(groupId)
    }

    private fun Contribution.toEntity(): ContributionEntity {
        return ContributionEntity(
            id = id,
            groupId = groupId,
            memberId = memberId,
            amount = amount,
            paymentDate = paymentDate,
            description = description,
            status = status,
            transactionId = transactionId,
            createdAt = createdAt
        )
    }

    private fun ContributionEntity.toDomain(): Contribution {
        return Contribution(
            id = id,
            groupId = groupId,
            memberId = memberId,
            amount = amount,
            paymentDate = paymentDate,
            description = description,
            status = status,
            transactionId = transactionId,
            createdAt = createdAt
        )
    }
}
