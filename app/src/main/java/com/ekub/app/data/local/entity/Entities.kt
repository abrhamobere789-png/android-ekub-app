package com.ekub.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val profilePicUrl: String = "",
    val createdAt: Long,
    val updatedAt: Long
)

@Entity(tableName = "ekub_groups")
data class EkubGroupEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val createdBy: String,
    val totalMembers: Int,
    val totalSavings: Double,
    val currency: String = "ETB",
    val payoutFrequency: String,
    val nextPayoutDate: Long,
    val status: String,
    val createdAt: Long,
    val updatedAt: Long
)

@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val groupId: String,
    val name: String,
    val email: String,
    val phone: String,
    val joinedAt: Long,
    val totalContributions: Double,
    val status: String
)

@Entity(tableName = "contributions")
data class ContributionEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val memberId: String,
    val amount: Double,
    val paymentDate: Long,
    val description: String = "",
    val status: String,
    val transactionId: String = "",
    val createdAt: Long
)

@Entity(tableName = "payouts")
data class PayoutEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val recipientId: String,
    val amount: Double,
    val payoutDate: Long,
    val status: String,
    val description: String = "",
    val createdAt: Long
)

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val fromUserId: String,
    val toUserId: String,
    val amount: Double,
    val type: String,
    val status: String,
    val timestamp: Long
)
