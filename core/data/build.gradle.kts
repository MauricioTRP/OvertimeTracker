import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.ott.android.library)
    alias(libs.plugins.ott.android.hilt)
    alias(libs.plugins.ott.jvm.retrofit)
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

android {
    namespace = "com.kotlinpl.core.data"


    defaultConfig {
        buildConfigField("String", "CLIENT_ID", "\"$clientId\"")
        buildConfigField("String", "CLIENT_SECRET", "\"$clientSecret\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(projects.core.domain)
    api(projects.core.database)

    // Data Store
    implementation(libs.datastore.preferences)

    // crypto
    implementation(libs.androidx.security.crypto)
}