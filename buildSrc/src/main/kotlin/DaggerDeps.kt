@Suppress("MemberVisibilityCanBePrivate")
object DaggerDeps : DepsCollection {

    private const val DaggerVersion = "2.25.2"

    const val dagger = "com.google.dagger:dagger:${DaggerVersion}"
    const val android = "com.google.dagger:dagger-android:${DaggerVersion}"
    const val androidSupport = "com.google.dagger:dagger-android-support:${DaggerVersion}"
    const val compiler = "com.google.dagger:dagger-compiler:${DaggerVersion}"
    const val annotationProcessor = "com.google.dagger:dagger-android-processor:${DaggerVersion}"

    override val all = listOf(
        Config.Impl(dagger),
        Config.Impl(android),
        Config.Impl(androidSupport),
        Config.Kapt(compiler),
        Config.Kapt(annotationProcessor)
    )
}