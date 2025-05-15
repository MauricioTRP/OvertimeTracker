plugins {
    alias(libs.plugins.ott.android.library)
    alias(libs.plugins.ott.android.hilt)
    alias(libs.plugins.ott.jvm.retrofit)
}

android {
    namespace = "com.kotlinpl.core.data"
}

dependencies {

    implementation(projects.core.domain)
    api(projects.core.database)
}