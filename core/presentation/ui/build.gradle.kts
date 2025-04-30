plugins {
    alias(libs.plugins.ott.android.library.compose)
}

android {
    namespace = "com.kotlinpl.core.presentation.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}