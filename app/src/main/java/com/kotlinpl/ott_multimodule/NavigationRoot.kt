package com.kotlinpl.ott_multimodule

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotlinpl.auth.presentation.intro.IntroScreenRoot
import com.kotlinpl.auth.presentation.login.LoginScreenRoot
import com.kotlinpl.auth.presentation.login.LoginViewModel
import com.kotlinpl.auth.presentation.register.RegisterScreenRoot
import com.kotlinpl.booking.presentation.BookingScreenRoot
import com.kotlinpl.booking.presentation.activities.ActivitiesScreenRoot
import com.kotlinpl.booking.presentation.single_activity.SingleActivityScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = if(!isLoggedIn) AuthScreens.Root.route else BookingScreens.Root.route,
    ) {
        bookingGraph(navController)
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
                onLoginClick = {
                    navController.navigate(AuthScreens.Login.route) {
                        popUpTo(route = AuthScreens.Register.route) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate(BookingScreens.Root.route)
                },
                viewModel = hiltViewModel()
            )
        }

        composable(route = AuthScreens.Login.route) {
            LoginScreenRoot(
                onSuccessfulLogin = {
                    navController.navigate(BookingScreens.Root.route)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreens.Register.route) {
                        popUpTo(route = AuthScreens.Login.route) {
                            inclusive = true
                            saveState = true
                        }

                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.bookingGraph(navController: NavHostController) {
    navigation(
        startDestination = BookingScreens.Activities.route,
        route = BookingScreens.Root.route
    ) {
        composable(BookingScreens.Home.route) {
            BookingScreenRoot(
                onActivitySearchClick = { /*TODO()*/ },
                onProfileClick = { /*TODO()*/ },
                onSaveClick = { /*TODO()*/ },
                modifier = Modifier
            )
        }

        composable(BookingScreens.Activities.route) {
            ActivitiesScreenRoot(
                viewModel = hiltViewModel(),
                onNavigateToActivity = { activity ->
                    val activityId = activity.id
                    val singleActivityRoute = BookingScreens.SingleActivity(activityId)

                    navController.navigate(
                        route = "${singleActivityRoute.route}/${singleActivityRoute.id}"
                    ) {
                        popUpTo(route = BookingScreens.Activities.route) {
                            inclusive = false
                            saveState = true
                        }

                        restoreState = true
                    }
                }
            )
        }

        /**
         * Single Activity Screen
         */
        composable(route = "${BookingScreens.SingleActivity().route}/{singleActivity}") {
            val activityId = it.arguments?.getString("singleActivity")

            SingleActivityScreenRoot(
                activityId = activityId.toString(),
                viewModel = hiltViewModel()
            )
        }
    }
}