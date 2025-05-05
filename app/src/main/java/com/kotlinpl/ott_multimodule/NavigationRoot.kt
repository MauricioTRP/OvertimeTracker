package com.kotlinpl.ott_multimodule

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotlinpl.auth.presentation.intro.IntroScreenRoot
import com.kotlinpl.auth.presentation.register.RegisterScreenRoot
import com.kotlinpl.ott_multimodule.AuthScreens

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreens.Root.route
    ) {
        authGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = AuthScreens.Intro.route,
        route = AuthScreens.Root.route
    ) {
        composable(route = AuthScreens.Intro.route) {
            IntroScreenRoot(
                onSignInClick = {
                    navController.navigate(AuthScreens.Login.route) {
                        popUpTo(route = AuthScreens.Register.route) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSignUpClick = {
                    navController.navigate(AuthScreens.Register.route)
                }
            )
        }

        composable(route = AuthScreens.Register.route) {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate(AuthScreens.Login.route) {
                        popUpTo(route = AuthScreens.Register.route) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {},
            )
        }
    }
}
