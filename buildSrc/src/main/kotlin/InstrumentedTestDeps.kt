@Suppress("MemberVisibilityCanBePrivate")
object InstrumentedTestDeps : DepsCollection{

    const val rules = "androidx.test:rules:1.2.0"
    const val runner = "androidx.test:runner:1.2.0"
    const val junit = "androidx.test.ext:junit:1.1.1"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:3.2.0"
    const val archCore = "androidx.arch.core:core-testing:2.1.0"
    const val mockk = "io.mockk:mockk-android:1.9.3"
    const val fixtures = "com.flextrade.jfixture:jfixture:2.7.2"

    override val all : List<Config> = listOf(
        Config.AndroidTestImpl(rules),
        Config.AndroidTestImpl(runner),
        Config.AndroidTestImpl(junit),
        Config.AndroidTestImpl(espressoCore),
        Config.AndroidTestImpl(espressoIntents),
        Config.AndroidTestImpl(archCore),
        Config.AndroidTestImpl(mockk),
        Config.AndroidTestImpl(fixtures)
    )
}