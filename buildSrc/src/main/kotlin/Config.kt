import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

sealed class Config(open val name: String) {
    data class Api(override val name: String) : Config(name)
    data class Impl(override val name: String) : Config(name)
    data class DebugImpl(override val name: String) : Config(name)
    data class Kapt(override val name: String) : Config(name)
    data class TestImpl(override val name: String) : Config(name)
    data class AndroidTestImpl(override val name: String) : Config(name)
}

interface DepsCollection {

    val all: List<Config>

    fun applyAllDependenciesTo(handler: DependencyHandlerScope) {
        all.applyAllTo(handler)
    }
}

fun DependencyHandler.apply(dependency: Config) {

    when (dependency) {
        is Config.Api -> add("api", dependency.name)
        is Config.Impl -> add("implementation", dependency.name)
        is Config.DebugImpl -> add("debugImplementation", dependency.name)
        is Config.Kapt -> add("kapt", dependency.name)
        is Config.TestImpl -> add("testImplementation", dependency.name)
        is Config.AndroidTestImpl -> add("androidTestImplementation", dependency.name)
    }
}

fun List<Config>.applyAllTo(handler : DependencyHandlerScope){
    forEach { dep -> handler.dependencies.apply(dep) }
}