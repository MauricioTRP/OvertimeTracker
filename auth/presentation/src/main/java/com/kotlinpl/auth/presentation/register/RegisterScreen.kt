package com.kotlinpl.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinpl.core.presentation.designsystem.OTT_MultimoduleTheme
import com.kotlinpl.core.presentation.designsystem.components.GradientBackground
import com.kotlinpl.auth.presentation.R
import com.kotlinpl.core.presentation.designsystem.components.OttActionButton
import com.kotlinpl.core.presentation.designsystem.components.OttPasswordTextField
import com.kotlinpl.core.presentation.designsystem.components.OttTextField
import com.kotlinpl.auth.domain.PasswordValidationState
import com.kotlinpl.auth.domain.UserDataValidator
import com.kotlinpl.core.presentation.ui.ObserveAsEvents

@Composable
fun RegisterScreenRoot(
    onLoginClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
    viewModel: RegisterViewModel
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is RegisterEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            RegisterEvent.RegistrationSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.registration_successful_you_can_login,
                    Toast.LENGTH_LONG
                ).show()
                onSuccessfulRegistration()
            }
        }
    }

    RegisterScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                RegisterAction.OnLoginClick -> {
                    onLoginClick()
                }
                RegisterAction.OnRegisterClick -> viewModel.onAction(action)
                RegisterAction.OnTogglePasswordVisibilityClick -> viewModel.onAction(action)
            }
       },
        onSuccessfulRegistration = onSuccessfulRegistration
    )
}


/**
 * Register Screen Composable
 *
 * @param [state] Accepts [RegisterState] to update UI accordingly
 * @param [onAction] Lambda to trigger Register actions based on [RegisterAction]
 * @param [onSuccessfulRegistration] Used to navigate on Successful registration
 */
@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    onSuccessfulRegistration: () -> Unit // used to navigate
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
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    append(stringResource(id = R.string.already_have_an_account) + " ")
                    pushStringAnnotation(
                        tag = "clickable_text",
                        annotation = stringResource(id = R.string.login)
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        append(stringResource(id = R.string.login))
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
                            onAction(RegisterAction.OnLoginClick)
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
                    onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                },
                hint = stringResource(R.string.must_be_valid_password),
                title = stringResource(com.kotlinpl.core.presentation.designsystem.R.string.password),
                fieldDescription = stringResource(R.string.passwordDescription),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirement(
                text = stringResource(R.string.at_least_x_characters, UserDataValidator.MIN_PASSWORD_LENGTH),
                isValid = state.passwordValidationState.hasMinLength
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirement(
                text = stringResource(R.string.at_least_one_number, UserDataValidator.MIN_PASSWORD_LENGTH),
                isValid = state.passwordValidationState.hasNumber
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirement(
                text = stringResource(R.string.at_least_one_uppercase, UserDataValidator.MIN_PASSWORD_LENGTH),
                isValid = state.passwordValidationState.hasUpperCaseCharacter
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirement(
                text = stringResource(R.string.at_least_one_lowecase, UserDataValidator.MIN_PASSWORD_LENGTH),
                isValid = state.passwordValidationState.hasLowerCaseCharacter
            )

            Spacer(modifier = Modifier.height(32.dp))

            OttActionButton(
                text = stringResource(R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(RegisterAction.OnRegisterClick)
                },
                buttonDescription = stringResource(R.string.register)
            )
        }
    }
}

@Composable
fun PasswordRequirement(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if(isValid) {
                Icons.Default.Check
            } else {
                Icons.Default.Close
            },
            contentDescription = null,
            tint = if (isValid) Green else Red
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    OTT_MultimoduleTheme {
        RegisterScreen(
            state = RegisterState(
                passwordValidationState = PasswordValidationState(
                    hasNumber = true,
                    hasMinLength = true,
                    hasLowerCaseCharacter = true,
                    hasUpperCaseCharacter = true
                )
            ),
            onAction = {},
            onSuccessfulRegistration = {}
        )
    }
}