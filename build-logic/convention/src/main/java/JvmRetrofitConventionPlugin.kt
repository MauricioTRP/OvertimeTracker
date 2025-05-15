import com.kotlinpl.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                "implementation"(libs.findBundle("retrofit").get())
                "testImplementation"(libs.findLibrary("kotlinx-coroutines-test").get())
            }
        }
    }
}