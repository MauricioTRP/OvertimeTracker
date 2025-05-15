import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.ott.android.application.compose)
    alias(libs.plugins.ott.android.hilt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.kotlinpl.ott_multimodule"

    defaultConfig {
        buildConfigField("String", "CLIENT_ID", "\"$clientId\"")
        buildConfigField("String", "CLIENT_ID", "\"$clientSecret\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

/**
 * Properties
 */
val localProperties = Properties().apply {
    val localFile = rootProject.file("local.properties")
    if (localFile.exists()) {
        load(localFile.inputStream())
    }
}

val clientId = localProperties.getProperty("CLIENT_ID")
val clientSecret = localProperties.getProperty("CLIENT_SECRET")

dependencies {
    // Auth Module
    implementation(projects.auth.domain)
    implementation(projects.auth.presentation)
    implementation(projects.auth.data)

    // Work Hours Modules
    implementation(projects.workhours.domain)
    implementation(projects.workhours.data)
    implementation(projects.workhours.presentation)

    // Booking Modules
    implementation(projects.booking.domain)
    implementation(projects.booking.data)
    implementation(projects.booking.presentation)
    implementation(projects.booking.network)

    // Core Modules
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.database)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // App dependencies
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)
}