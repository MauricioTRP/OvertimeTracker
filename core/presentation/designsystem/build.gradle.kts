plugins {
    alias(libs.plugins.ott.android.library.compose)
}

android {
    namespace = "com.kotlinpl.core.presentation.designsystem"
}

dependencies {
    api(libs.androidx.material3)
}