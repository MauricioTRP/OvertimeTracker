import com.kotlinpl.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "ott.android.library.compose")

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}