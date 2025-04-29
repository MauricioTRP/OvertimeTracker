plugins {
    alias(libs.plugins.ott.android.library.compose)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.kotlinpl.core.presentation.designsystem"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.androidx.material3)
}