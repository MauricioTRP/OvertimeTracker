plugins {
    alias(libs.plugins.ott.android.library.compose)
}

android {
    namespace = "com.kotlinpl.core.presentation.ui"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}