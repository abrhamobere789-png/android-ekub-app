package com.ekub.app.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val profilePicUrl: String = "",
    val createdAt: Long,
    val updatedAt: Long
)

data class EkubGroup(
    val id: String,
    val name: String,
    val description: String,
    val createdBy: String,
    val totalMembers: Int,
    val totalSavings: Double,
    val currency: String = "ETB",
    val payoutFrequency: String, // Monthly, Quarterly, etc.
    val nextPayoutDate: Long,
    val status: String, // Active, Inactive, Completed
    val createdAt: Long,
    val updatedAt: Long
)

data class Member(
    val id: String,
    val userId: String,
    val groupId: String,
    val name: String,
    val email: String,
    val phone: String,
    val joinedAt: Long,
    val totalContributions: Double,
    val status: String // Active, Inactive, Left
)

data class Contribution(
    val id: String,
    val groupId: String,
    val memberId: String,
    val amount: Double,
    val paymentDate: Long,
    val description: String = "",
    val status: String, // Pending, Completed, Failed
    val transactionId: String = "",
    val createdAt: Long
)

data class Payout(
    val id: String,
    val groupId: String,
    val recipientId: String,
    val amount: Double,
    val payoutDate: Long,
    val status: String, // Pending, Completed, Failed
    val description: String = "",
    val createdAt: Long
)

data class Transaction(
    val id: String,
    val groupId: String,
    val fromUserId: String,
    val toUserId: String,
    val amount: Double,
    val type: String, // Contribution, Payout, Transfer
    val status: String,
    val timestamp: Long
)
