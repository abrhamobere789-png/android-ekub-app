package com.ekub.app.data.repository

import com.ekub.app.data.local.dao.UserDao
import com.ekub.app.data.local.entity.UserEntity
import com.ekub.app.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }

    fun getUserById(userId: String): Flow<User?> {
        return userDao.getUserById(userId).map { it?.toDomain() }
    }

    fun getUserByEmail(email: String): Flow<User?> {
        return userDao.getUserByEmail(email).map { it?.toDomain() }
    }

    fun getCurrentUser(): Flow<User?> {
        return userDao.getCurrentUser().map { it?.toDomain() }
    }

    suspend fun deleteUser(userId: String) {
        // Implement delete logic
    }

    suspend fun logout() {
        userDao.deleteAllUsers()
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            name = name,
            email = email,
            phone = phone,
            profilePicUrl = profilePicUrl,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun UserEntity.toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            phone = phone,
            profilePicUrl = profilePicUrl,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
