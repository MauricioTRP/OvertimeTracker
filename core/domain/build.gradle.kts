plugins {
    alias(libs.plugins.ott.jvm.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    implementation(libs.gson)
    implementation(libs.kotlinx.serialization)
}