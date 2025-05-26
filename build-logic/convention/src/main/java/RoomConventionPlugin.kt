import androidx.room.gradle.RoomExtension
import com.android.utils.TraceUtils.simpleId
import com.google.devtools.ksp.gradle.KspExtension
import com.kotlinpl.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.apply

class RoomConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "androidx.room")
            apply(plugin = "com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                arg("room.generateKotlin", "true")
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                "ksp"(libs.findLibrary("room.ksp").get())
                "implementation"(libs.findLibrary("room.runtime").get())
                "implementation"(libs.findLibrary("room.ktx").get())
                "implementation"(libs.findLibrary("room.paging").get())
                "testImplementation"(libs.findLibrary("room.testing").get())
            }
        }
    }
}