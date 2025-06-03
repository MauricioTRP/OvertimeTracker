package com.kotlinpl.core.domain.replacement

import kotlinx.coroutines.flow.Flow

typealias ReplacementId = String

interface LocalReplacementDataSource {
    suspend fun getReplacements() : Flow<List<Replacement>>
    suspend fun upsertReplacement(replacement: Replacement)
    suspend fun upsertReplacements(replacements: List<Replacement>)
    suspend fun deleteReplacement(id: String)
    suspend fun deleteAllReplacements()
}