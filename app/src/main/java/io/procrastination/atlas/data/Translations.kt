package io.procrastination.atlas.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Translations(
    val br: String? = null,
    val de: String? = null,
    val es: String? = null,
    val fa: String? = null,
    val fr: String? = null,
    val hr: String? = null,
    @Json(name = "it") val italian: String? = null,
    val ja: String? = null,
    val nl: String? = null,
    val pt: String? = null
)