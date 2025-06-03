package com.kotlinpl.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
//
//@Entity
//data class ReplacementPendingSyncEntity(
//    @Embedded val replacement: ReplacementEntity,
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val replacementId: Int = replacement.id,
//    val userId: String
//) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as ReplacementPendingSyncEntity
//
//        return super.equals(other)
//    }
//
//    override fun hashCode(): Int {
//        var result = replacement.hashCode()
//        result = 31 * result + replacementId.hashCode()
//        result = 31 * result + userId.hashCode()
//        return result
//    }
//}
