plugins {
    alias(libs.plugins.ott.android.library)
    alias(libs.plugins.ott.android.hilt)
    alias(libs.plugins.ott.jvm.ktor)
}

android {
    namespace = "com.kotlinpl.core.data"
}

dependencies {

    implementation(projects.core.domain)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    api(projects.core.database)
}