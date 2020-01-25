package io.procrastination.atlas.helpers

import com.flextrade.jfixture.JFixture
import io.procrastination.atlas.data.CountryDto

object FixtureHelper {

    private val fixture = JFixture()

    fun fxtCountryDto(
        capital: String = fixture.create(String::class.java),
        population: Int = fixture.create(Int::class.java),
        latitude: Double = fixture.create(Double::class.java),
        longitude: Double = fixture.create(Double::class.java)
    ): CountryDto {

        return CountryDto(
            alpha2Code = fixture.create(String::class.java),
            alpha3Code = fixture.create(String::class.java),
            altSpellings = emptyList(),
            area = fixture.create(Double::class.java),
            borders = emptyList(),
            callingCodes = emptyList(),
            capital = capital,
            cioc = fixture.create(String::class.java),
            currencies = emptyList(),
            demonym = fixture.create(String::class.java),
            flag = fixture.create(String::class.java),
            gini = fixture.create(Double::class.java),
            languages = emptyList(),
            latlng = listOf(latitude, longitude),
            name = fixture.create(String::class.java),
            nativeName = fixture.create(String::class.java),
            numericCode = fixture.create(String::class.java),
            population = population,
            region = fixture.create(String::class.java),
            regionalBlocs = emptyList(),
            subregion = fixture.create(String::class.java),
            timezones = emptyList(),
            topLevelDomain = emptyList(),
            translations = null
        )
    }
}