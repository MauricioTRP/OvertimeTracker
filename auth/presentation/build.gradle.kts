plugins {
    alias(libs.plugins.ott.feature.ui)
}

android {
    namespace = "com.kotlinpl.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}