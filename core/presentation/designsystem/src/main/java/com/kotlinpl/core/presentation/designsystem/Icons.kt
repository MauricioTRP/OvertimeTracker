package com.kotlinpl.core.presentation.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

val LockIcon : ImageVector
    @Composable
    get() = Icons.Default.Lock

val EyeClosed : ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.eye_closed)

val EyeOpen : ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.eye_opened)