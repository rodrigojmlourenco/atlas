@Suppress("MemberVisibilityCanBePrivate")
object PlayServicesDeps : DepsCollection {

    const val core = "com.google.android.play:core:1.6.4"
    const val locationAndActivityRecog = "com.google.android.gms:play-services-location:17.0.0"

    override val all = listOf(
        Config.Api(core),
        Config.Impl(locationAndActivityRecog)
    )
}