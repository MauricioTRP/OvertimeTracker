plugins {
    alias(libs.plugins.ott.android.library)
    alias(libs.plugins.ott.room)
}

android {
    namespace = "com.kotlinpl.core.database"
}

dependencies {
    implementation(projects.core.domain)
}