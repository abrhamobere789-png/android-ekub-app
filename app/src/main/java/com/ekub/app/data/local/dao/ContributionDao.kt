package com.ekub.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ekub.app.data.local.entity.ContributionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContributionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContribution(contribution: ContributionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributions(contributions: List<ContributionEntity>)

    @Update
    suspend fun updateContribution(contribution: ContributionEntity)

    @Delete
    suspend fun deleteContribution(contribution: ContributionEntity)

    @Query("SELECT * FROM contributions WHERE id = :contributionId")
    fun getContributionById(contributionId: String): Flow<ContributionEntity?>

    @Query("SELECT * FROM contributions WHERE groupId = :groupId ORDER BY createdAt DESC")
    fun getContributionsByGroupId(groupId: String): Flow<List<ContributionEntity>>

    @Query("SELECT * FROM contributions WHERE memberId = :memberId ORDER BY createdAt DESC")
    fun getContributionsByMemberId(memberId: String): Flow<List<ContributionEntity>>

    @Query("SELECT * FROM contributions WHERE status = :status ORDER BY createdAt DESC")
    fun getContributionsByStatus(status: String): Flow<List<ContributionEntity>>

    @Query("DELETE FROM contributions WHERE groupId = :groupId")
    suspend fun deleteContributionsByGroupId(groupId: String)
}
