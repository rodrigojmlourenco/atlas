package io.procrastination.atlas.data

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class LanguageDto(
    val iso639_1: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
)