package com.kotlinpl.core.data.auth

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.kotlinpl.core.domain.session.AuthSessionInfo
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

/**
 * Used if protobuf where implemented
 */
object AuthInfoSerializer : Serializer<AuthSessionInfo> {
    override suspend fun readFrom(input: InputStream): AuthSessionInfo {
        try {
            return Json.decodeFromString(
                AuthSessionInfo.serializer(), input.readBytes().decodeToString()
            )
        } catch (serializationException: SerializationException) {
            throw CorruptionException("Unable to read AuthInfo", serializationException)
        }
    }

    override suspend fun writeTo(
        t: AuthSessionInfo,
        output: OutputStream
    ) {
        Json.encodeToString(AuthSessionInfo.serializer(), t)
            .encodeToByteArray()
    }

    override val defaultValue: AuthSessionInfo = AuthSessionInfo(
        accessToken = "",
        refreshToken = "",
        clientId = "",
        expiresIn = 0L,
        tokenType = "Bearer",
        clientSecret = ""
    )
}