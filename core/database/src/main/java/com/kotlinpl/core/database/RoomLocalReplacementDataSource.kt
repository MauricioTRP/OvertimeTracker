package com.kotlinpl.core.database
//
//import com.kotlinpl.core.database.dao.ReplacementDao
//import com.kotlinpl.core.domain.replacement.LocalReplacementDataSource
//import com.kotlinpl.core.domain.replacement.Replacement
//import kotlinx.coroutines.flow.Flow
//
//class RoomLocalReplacementDataSource(
//    private val replacementDao: ReplacementDao
//) : LocalReplacementDataSource {
//    override suspend fun getReplacements(): Flow<List<Replacement>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun upsertReplacement(replacement: Replacement) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun upsertReplacements(replacements: List<Replacement>) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteReplacement(id: String) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteAllReplacements() {
//        TODO("Not yet implemented")
//    }
//}