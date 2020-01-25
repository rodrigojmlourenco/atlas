package io.procrastination.atlas.data

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class CountryDto(
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<CurrencyDto>,
    val demonym: String,
    val flag: String,
    val gini: Double,
    val languages: List<LanguageDto>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val regionalBlocs: List<RegionalBlocDto>,
    val subregion: String,
    val timezones: List<String>,
    val topLevelDomain: List<String>,
    val translations: Translations
)