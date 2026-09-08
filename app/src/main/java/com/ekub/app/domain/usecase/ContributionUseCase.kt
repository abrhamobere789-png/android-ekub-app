package com.ekub.app.domain.usecase

import com.ekub.app.data.repository.ContributionRepository
import com.ekub.app.domain.model.Contribution
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContributionsByGroupIdUseCase @Inject constructor(
    private val contributionRepository: ContributionRepository
) {
    operator fun invoke(groupId: String): Flow<List<Contribution>> {
        return contributionRepository.getContributionsByGroupId(groupId)
    }
}

class GetContributionsByMemberIdUseCase @Inject constructor(
    private val contributionRepository: ContributionRepository
) {
    operator fun invoke(memberId: String): Flow<List<Contribution>> {
        return contributionRepository.getContributionsByMemberId(memberId)
    }
}

class SaveContributionUseCase @Inject constructor(
    private val contributionRepository: ContributionRepository
) {
    suspend operator fun invoke(contribution: Contribution) {
        contributionRepository.saveContribution(contribution)
    }
}
