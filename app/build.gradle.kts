plugins {
    alias(libs.plugins.ott.android.application.compose)
}

android {
    namespace = "com.kotlinpl.ott_multimodule"

    defaultConfig {
        applicationId = "com.kotlinpl.ott_multimodule"
        versionCode = 1
        versionName = "1.0"

    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}