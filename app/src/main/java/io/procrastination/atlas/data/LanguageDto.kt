package io.procrastination.atlas.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class LanguageDto(
    @Json(name="iso639_1") val iso639Var1: String?,
    @Json(name="iso639_2") val iso639Var2: String?,
    val name: String?,
    val nativeName: String?
)