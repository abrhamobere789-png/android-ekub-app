package com.ekub.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekub.app.data.local.dao.ContributionDao
import com.ekub.app.data.local.dao.EkubGroupDao
import com.ekub.app.data.local.dao.UserDao
import com.ekub.app.data.local.entity.ContributionEntity
import com.ekub.app.data.local.entity.EkubGroupEntity
import com.ekub.app.data.local.entity.MemberEntity
import com.ekub.app.data.local.entity.PayoutEntity
import com.ekub.app.data.local.entity.TransactionEntity
import com.ekub.app.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        EkubGroupEntity::class,
        MemberEntity::class,
        ContributionEntity::class,
        PayoutEntity::class,
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EkubDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun groupDao(): EkubGroupDao
    abstract fun contributionDao(): ContributionDao
}
