package io.procrastination.atlas.data

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class CurrencyDto(
    val code: String?,
    val name: String?,
    val symbol: String?
)