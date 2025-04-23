import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.apply
import com.kotlinpl.convention.configureAndroidKotlin

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "kotlin-android")

            extensions.configure<ApplicationExtension> {
                configureAndroidKotlin(this)

                defaultConfig.targetSdk = 35
            }
        }
    }
}