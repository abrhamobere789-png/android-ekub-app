package com.ekub.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ekub.app.data.local.entity.EkubGroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EkubGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: EkubGroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroups(groups: List<EkubGroupEntity>)

    @Update
    suspend fun updateGroup(group: EkubGroupEntity)

    @Delete
    suspend fun deleteGroup(group: EkubGroupEntity)

    @Query("SELECT * FROM ekub_groups WHERE id = :groupId")
    fun getGroupById(groupId: String): Flow<EkubGroupEntity?>

    @Query("SELECT * FROM ekub_groups ORDER BY createdAt DESC")
    fun getAllGroups(): Flow<List<EkubGroupEntity>>

    @Query("SELECT * FROM ekub_groups WHERE status = :status ORDER BY createdAt DESC")
    fun getGroupsByStatus(status: String): Flow<List<EkubGroupEntity>>

    @Query("DELETE FROM ekub_groups")
    suspend fun deleteAllGroups()
}
