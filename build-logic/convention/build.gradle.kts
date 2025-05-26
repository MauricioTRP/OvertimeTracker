import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11

plugins {
    `kotlin-dsl`
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConventionPlugin") {
            id = libs.plugins.ott.android.application.asProvider().get().pluginId

            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = libs.plugins.ott.android.application.compose.get().pluginId

            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryConventionPlugin") {
            id = libs.plugins.ott.android.library.asProvider().get().pluginId

            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryComposeConventionPlugin") {
            id = libs.plugins.ott.android.library.compose.get().pluginId

            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.ott.android.hilt.get().pluginId

            implementationClass = "HiltConventionPlugin"
        }
        register("androidFeatureUi") {
            id = libs.plugins.ott.feature.ui.get().pluginId

            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("jvmLibrary") {
            id = libs.plugins.ott.jvm.library.get().pluginId

            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmRetrofit") {
            id = libs.plugins.ott.jvm.retrofit.get().pluginId

            implementationClass = "JvmRetrofitConventionPlugin"
        }
        register("room") {
            id = libs.plugins.ott.room.get().pluginId

            implementationClass = "RoomConventionPlugin"
        }
    }
}