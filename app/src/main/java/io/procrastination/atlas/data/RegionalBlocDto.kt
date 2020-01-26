package io.procrastination.atlas.data

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class RegionalBlocDto(
    val acronym: String?,
    val name: String?,
    val otherAcronyms: List<String>?,
    val otherNames: List<String>?
)