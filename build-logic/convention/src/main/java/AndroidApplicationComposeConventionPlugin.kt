import com.android.build.api.dsl.ApplicationExtension
import com.kotlinpl.convention.configureAndroidCompose
import com.kotlinpl.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "ott.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<ApplicationExtension>()

            configureAndroidCompose(extension)

            dependencies {
                "implementation"(libs.findLibrary("androidx-activity-compose").get())
                "testImplementation"(libs.findLibrary("junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx-junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx-espresso-core").get())
                "androidTestImplementation"(libs.findLibrary("androidx-ui-test-junit4").get())
                "debugImplementation"(libs.findLibrary("androidx-ui-test-manifest").get())
            }
        }
    }
}