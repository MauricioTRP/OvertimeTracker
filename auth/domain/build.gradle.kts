plugins {
    alias(libs.plugins.ott.jvm.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    implementation(projects.core.domain)

    // Dagger
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}