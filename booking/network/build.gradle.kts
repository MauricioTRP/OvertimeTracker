plugins {
    alias(libs.plugins.ott.android.library)
    alias(libs.plugins.ott.android.hilt)
    alias(libs.plugins.ott.jvm.retrofit)
}

android {
    namespace = "com.kotlinpl.booking.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}