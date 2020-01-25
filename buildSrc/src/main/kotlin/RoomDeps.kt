@Suppress("MemberVisibilityCanBePrivate")
object RoomDeps : DepsCollection {
    private const val ROOM = "2.2.2"
    const val core = "androidx.room:room-runtime:${ROOM}"
    const val compiler = "androidx.room:room-compiler:${ROOM}"
    const val ktx = "androidx.room:room-ktx:${ROOM}"
    const val rx = "androidx.room:room-rxjava2:${ROOM}"
    const val test = "androidx.room:room-testing:${ROOM}"

    override val all = listOf(
        Config.Impl(core),
        Config.Kapt(compiler),
        Config.Impl(ktx),
        Config.Impl(rx),
        Config.TestImpl(rx),
        Config.AndroidTestImpl(rx)
    )
}