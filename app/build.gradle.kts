plugins {
    alias(libs.plugins.ott.android.application.compose)
}

android {
    namespace = "com.kotlinpl.ott_multimodule"

}

dependencies {
    implementation(projects.core.presentation.designsystem)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}