package com.kotlinpl.core.domain.replacement

import kotlinx.coroutines.flow.Flow

typealias ReplacementId = String

interface LocalReplacementDataSource {
    fun getReplacements() : Flow<List<Replacement>>
    suspend fun upsertReplacement(replacement: Replacement)
    suspend fun deleteReplacement(id: String)
}