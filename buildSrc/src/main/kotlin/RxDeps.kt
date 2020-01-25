@Suppress("MemberVisibilityCanBePrivate")
object RxDeps : DepsCollection{
    private const val RxAndroidVersion = "2.1.1"
    private const val RxJavaVersion = "2.2.15"
    private const val RxKotlinVersion = "2.4.0"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${RxJavaVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${RxKotlinVersion}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${RxAndroidVersion}"

    override val all = listOf(
        Config.Impl(rxJava),
        Config.Impl(rxKotlin),
        Config.Impl(rxAndroid)
    )
}