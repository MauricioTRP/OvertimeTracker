plugins {
    alias(libs.plugins.ott.android.library)
    alias(libs.plugins.ott.android.hilt)
}

android {
    namespace = "com.kotlinpl.auth.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.auth.domain)

    // Firebase and FirebaseAuth
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}