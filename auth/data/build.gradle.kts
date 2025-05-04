plugins {
    alias(libs.plugins.ott.android.library)
}

android {
    namespace = "com.kotlinpl.auth.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.auth.domain)
}