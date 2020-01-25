@Suppress("MemberVisibilityCanBePrivate")
object UxDeps : DepsCollection{
    const val circleImage = "de.hdodenhof:circleimageview:3.0.1"
    const val lottie = "com.airbnb.android:lottie:3.3.1"
    const val pinView = "com.chaos.view:pinview:1.4.3"

    override val all = listOf(
        Config.Impl(lottie),
        Config.Impl(circleImage),
        Config.Impl(pinView)
    )
}