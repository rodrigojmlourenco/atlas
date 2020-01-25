@Suppress("MemberVisibilityCanBePrivate")
object SupportDeps {

    const val easyPermissions = "pub.devrel:easypermissions:3.0.0"
    const val jodaTime = "net.danlew:android.joda:2.10.3"
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    val all = listOf(
        Config.Impl(easyPermissions),
        Config.Impl(jodaTime),
        Config.Impl(timber)
    )
}