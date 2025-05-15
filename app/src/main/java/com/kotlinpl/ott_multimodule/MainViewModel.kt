package com.kotlinpl.ott_multimodule

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.auth.domain.AuthService
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
    authService: AuthService
) : ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isCheckingAuth = true)
            state = state.copy(
                isLoggedIn = !authService.currentUserId.isEmpty(),
                isCheckingAuth = false
            )
            state = state.copy(isCheckingAuth = false)
        }
    }
}