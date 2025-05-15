plugins {
    alias(libs.plugins.ott.android.library)
    alias(libs.plugins.ott.android.hilt)
    alias(libs.plugins.ott.jvm.retrofit)
}

android {
    namespace = "com.kotlinpl.booking.data"
}

dependencies {
    // Data depends on :domain, :network and :database
    implementation(projects.booking.domain)
    implementation(projects.booking.network)
    implementation(projects.booking.database)

    // Core dependencies
    implementation(projects.core.domain)
    implementation(projects.core.data)

    // Data Store
    implementation(libs.datastore.preferences)
}
