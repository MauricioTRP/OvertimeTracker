package com.kotlinpl.core.data.auth

import com.kotlinpl.core.domain.session.AuthSessionInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {
    @FormUrlEncoded
    @POST("security/oauth2/token")
    suspend fun login(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String
    ) : Response<AccessTokenResponseDto>
}
