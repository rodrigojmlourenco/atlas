@Suppress("MemberVisibilityCanBePrivate")
object NetworkDeps : DepsCollection{

    private const val Retrofit = "2.7.0"
    private const val OkHttp = "4.2.2"
    private const val Moshi = "1.9.2"

    //--- Retrofit ---//
    const val retrofit = "com.squareup.retrofit2:retrofit:${Retrofit}"
    const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Retrofit}"

    //--- OkHttp ---//
    const val okHttp = "com.squareup.okhttp3:okhttp:${OkHttp}"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${OkHttp}"
    const val picasso = "com.squareup.picasso:picasso:2.71828"

    // Moshi
    const val moshi = "com.squareup.moshi:moshi:${Moshi}"
    const val moshiKt = "com.squareup.moshi:moshi-kotlin:${Moshi}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Moshi}"
    const val moshiKtCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Moshi}"

    override val all = listOf(
        Config.Impl(retrofit),
        Config.Impl(retrofitRx),
        Config.Impl(retrofitMoshi),

        Config.Impl(okHttp),
        Config.Impl(okHttpLogging),
        Config.Impl(picasso),
        Config.Impl(moshi),
        Config.Kapt(moshiKtCodeGen),
        Config.Impl(moshiAdapters)
    )
}