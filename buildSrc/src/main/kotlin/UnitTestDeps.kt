@Suppress("MemberVisibilityCanBePrivate")
object UnitTestDeps : DepsCollection {

    const val junit4 = "junit:junit:4.12"
    const val jFixtures = "com.flextrade.jfixture:jfixture:2.7.2"
    const val roboletric = "org.robolectric:robolectric:4.3.1"
    const val androidCore = "androidx.arch.core:core-testing:2.1.0"
    const val androidTestRunner = "androidx.test:runner:1.2.0"
    const val androidJunit = "androidx.test.ext:junit:1.1.1"
    const val mockk = "io.mockk:mockk:1.9.3"
    const val mockServer = "org.mock-server:mockserver-netty:5.7.2"

    override val all : List<Config> = listOf(
        Config.TestImpl(junit4),
        Config.TestImpl(jFixtures),
        Config.TestImpl(androidCore),
        Config.TestImpl(androidTestRunner),
        Config.TestImpl(androidJunit),
        Config.TestImpl(mockk),
        Config.TestImpl(mockServer),
        Config.TestImpl(roboletric)
    )
}