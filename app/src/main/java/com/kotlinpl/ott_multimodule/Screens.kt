package com.kotlinpl.ott_multimodule

import androidx.compose.ui.graphics.vector.ImageVector

interface Screens {
    val icon: ImageVector?
        get() = null
    val route: String
}

/**
 * Auth Screens
 */
sealed interface AuthScreens {
    data object Root : AuthScreens, Screens {
        override val route = "auth"
    }

    data object Intro : AuthScreens, Screens {
        override val route = "intro"
    }

    data object Register : AuthScreens, Screens {
        override val route = "register"
    }

    data object Login : AuthScreens, Screens {
        override val route = "sign_in"
    }
}