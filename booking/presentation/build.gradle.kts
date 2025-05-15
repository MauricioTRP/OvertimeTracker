plugins {
    alias(libs.plugins.ott.feature.ui)
    alias(libs.plugins.ott.android.hilt)
}

android {
    namespace = "com.kotlinpl.booking.presentation"
}

dependencies {
    // Core
    implementation(projects.core.domain)

    implementation(projects.booking.domain)
}