package com.kotlinpl.core.domain.replacement

import kotlin.time.Duration

interface SyncReplacementScheduler {
    suspend fun scheduleSync(type: SyncType)
    suspend fun cancelAllSyncs()

    sealed interface SyncType {
        data class FetchReplacements(val interval: Duration) : SyncType
        data class DeleteReplacement(val replacementId: ReplacementId) : SyncType
        class CreateReplacement(val replacement: Replacement) : SyncType
    }
}