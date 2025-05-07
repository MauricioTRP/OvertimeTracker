plugins {
    alias(libs.plugins.ott.android.application.compose)
    alias(libs.plugins.ott.android.hilt)
}

android {
    namespace = "com.kotlinpl.ott_multimodule"

}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.auth.presentation)
    implementation(projects.auth.data)

    // Core Modules
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.database)


    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}