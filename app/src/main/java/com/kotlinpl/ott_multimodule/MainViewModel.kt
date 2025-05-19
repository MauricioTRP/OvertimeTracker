package com.kotlinpl.ott_multimodule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.auth.domain.AuthService
import com.kotlinpl.core.data.auth.EncryptedSessionStorage
import com.kotlinpl.core.domain.session.AuthSessionInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * MainViewModel
 *
 * ViewModel for the Main screen
 * used to check if user is logged in
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    authService: AuthService,
    encryptedSessionStorage: EncryptedSessionStorage
) : ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    var authSessionInfo by mutableStateOf(AuthSessionInfo(
        accessToken = "",
        refreshToken = "",
        clientId = BuildConfig.OTT_CLIENT_ID,
        expiresIn = 0L,
        tokenType = "",
        clientSecret = BuildConfig.OTT_CLIENT_SECRET
    ))

    init {
        viewModelScope.launch {
            /**
             * Set initial data to be used on Retrofit constructor
             */
            encryptedSessionStorage.setInitialData(authSessionInfo)

            state = state.copy(isCheckingAuth = true)
            state = state.copy(
                isLoggedIn = !authService.currentUserId.isEmpty(),
                isCheckingAuth = false
            )
            state = state.copy(isCheckingAuth = false)
        }
    }
}