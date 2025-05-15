package com.kotlinpl.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotlinpl.auth.presentation.R
import com.kotlinpl.core.presentation.designsystem.OTT_MultimoduleTheme
import com.kotlinpl.core.presentation.designsystem.components.GradientBackground
import com.kotlinpl.core.presentation.designsystem.components.OttActionButton
import com.kotlinpl.core.presentation.designsystem.components.OttPasswordTextField
import com.kotlinpl.core.presentation.designsystem.components.OttTextField
import com.kotlinpl.core.presentation.ui.ObserveAsEvents

@Composable
fun LoginScreenRoot(
    onSignUpClick: () -> Unit,
    onSuccessfulLogin: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is LoginEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            is LoginEvent.LoginSuccess -> {
                Toast.makeText(
                    context,
                    R.string.login_successful,
                    Toast.LENGTH_LONG
                ).show()

                onSuccessfulLogin()
            }

        }
    }

    LoginScreen(
        state = viewModel.state,
        onAction = { viewModel.onAction(LoginActions.OnLoginClick) },
        onSuccessfulLogin = onSuccessfulLogin
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginActions) -> Unit,
    onSuccessfulLogin: () -> Unit // used to navigate to main screen after successful login
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.headlineMedium
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    append(stringResource(id = R.string.dont_have_an_account) + " ")
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.register)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        append(stringResource(id = R.string.register))
                    }
                }
            }

            Text(
                text = annotatedString,
                modifier = Modifier.clickable {
                    annotatedString.getStringAnnotations(
                        tag = "clickable_text",
                        start = 0,
                        end = annotatedString.length
                    )
                        .firstOrNull()?.let {
                            onAction(LoginActions.OnLoginClick)
                        }
                }
            )

            Spacer(modifier = Modifier.height(48.dp))

            OttTextField(
                state = state.email,
                startIcon = Icons.Default.Email,
                endIcon = if(state.isEmailValid) {
                    Icons.Default.Check
                } else { null },
                hint = stringResource(R.string.example_email),
                title = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(),
                additionalInfo = stringResource(R.string.must_be_valid_email),
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            OttPasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {
                    onAction(LoginActions.OnTogglePasswordVisibility)
                },
                hint = stringResource(R.string.must_be_valid_password),
                title = stringResource(com.kotlinpl.core.presentation.designsystem.R.string.password),
                fieldDescription = stringResource(R.string.passwordDescription),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            OttActionButton(
                text = stringResource(R.string.login),
                isLoading = state.isLoggingIn,
                enabled = state.canLogin,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(LoginActions.OnLoginClick)
                },
                buttonDescription = stringResource(R.string.login)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    OTT_MultimoduleTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {},
            onSuccessfulLogin = {}
        )
    }
}