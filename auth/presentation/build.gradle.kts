plugins {
    alias(libs.plugins.ott.feature.ui)
    alias(libs.plugins.ott.android.hilt)
}

android {
    namespace = "com.kotlinpl.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}