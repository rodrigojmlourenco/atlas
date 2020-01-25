@Suppress("MemberVisibilityCanBePrivate")
object JetpackDeps : DepsCollection {

    private const val AndroidX = "1.1.0"

    const val ktx = "androidx.core:core-ktx:${AndroidX}"
    const val appCompat = "androidx.appcompat:appcompat:${AndroidX}"
    const val browser = "androidx.browser:browser:1.0.0"
    const val recyclerView = "androidx.recyclerview:recyclerview:${AndroidX}"
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta1"
    const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.1.0"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:2.1.0"
    const val material = "com.google.android.material:material:1.1.0-rc01"
    const val flexbox = "com.google.android:flexbox:2.0.0"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:2.1.0"
    const val navUI = "androidx.navigation:navigation-ui-ktx:2.1.0"

    override val all = listOf(
        Config.Impl(ktx),
        Config.Impl(appCompat),
        Config.Impl(browser),
        Config.Impl(recyclerView),
        Config.Impl(cardView),
        Config.Impl(constraintLayout),
        Config.Impl(viewPager2),
        Config.Impl(lifecycleViewModel),
        Config.Impl(lifecycleExtensions),
        Config.Impl(navFragment),
        Config.Impl(navUI),
        Config.Api(material),
        Config.Impl(flexbox)
    )
}