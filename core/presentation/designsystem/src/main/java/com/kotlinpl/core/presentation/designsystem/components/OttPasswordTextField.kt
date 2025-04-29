package com.kotlinpl.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinpl.core.presentation.designsystem.EyeClosed
import com.kotlinpl.core.presentation.designsystem.EyeOpen
import com.kotlinpl.core.presentation.designsystem.LockIcon
import com.kotlinpl.core.presentation.designsystem.R

@Composable
fun OttPasswordTextField(
    state: TextFieldState,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    hint: String,
    title: String?,
    fieldDescription: String?,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    val fieldDescription = fieldDescription ?: stringResource(R.string.password)

    Column(
        modifier = modifier
            .semantics {
                contentDescription = fieldDescription
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (title != null) {
                Text(
                    text = title,
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.xs_padding)))
            BasicSecureTextField(
                state = state,
                textObfuscationMode = if (isPasswordVisible) {
                    TextObfuscationMode.Visible
                } else TextObfuscationMode.Hidden,
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.rounded_border)))
                    .padding(dimensionResource(R.dimen.medium_padding))
                    .onFocusChanged { isFocused = it.isFocused },
                decorator = { innerBox ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = LockIcon,
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            if (state.text.isEmpty() && !isFocused) {
                                Text(
                                    text = hint,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerBox()
                        }
                        IconButton(onClick = onTogglePasswordVisibility) {
                            Icon(
                                imageVector = if (!isPasswordVisible) {
                                    EyeClosed
                                } else EyeOpen,
                                contentDescription = if(isPasswordVisible) {
                                    stringResource(id = R.string.show_password)
                                } else {
                                    stringResource(id = R.string.hide_password)
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun OttPasswordTextFieldPreview() {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OttPasswordTextField(
        isPasswordVisible = isPasswordVisible,
        state = rememberTextFieldState(),
        onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
        hint = "Set password",
        title = null,
        fieldDescription = "Password"
    )
}