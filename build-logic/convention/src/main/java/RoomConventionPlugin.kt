import com.kotlinpl.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                "ksp"(libs.findLibrary("room.compiler").get())
                "implementation"(libs.findLibrary("room.runtime").get())
                "implementation"(libs.findLibrary("room.ktx").get())
                "implementation"(libs.findLibrary("room.paging").get())
                "test"(libs.findLibrary("room.testing").get())
            }
        }
    }
}