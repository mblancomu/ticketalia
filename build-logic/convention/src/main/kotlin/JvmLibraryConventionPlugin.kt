
import com.manuelblanco.mobilechallenge.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Manuel Blanco Murillo on 25/6/23.
 */
class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}
