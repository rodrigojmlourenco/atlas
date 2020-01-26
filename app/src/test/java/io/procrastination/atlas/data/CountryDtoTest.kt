package io.procrastination.atlas.data

import com.squareup.moshi.Moshi
import io.procrastination.atlas.helpers.FixtureHelper
import org.junit.Assert
import org.junit.Test

class CountryDtoTest {

    private val moshi = Moshi.Builder().build()

    @Test
    fun testSerialization(){
        val fxtCountry = FixtureHelper.fxtCountryDto()
        val adapter = moshi.adapter(CountryDto::class.java)

        val test = adapter.toJson(fxtCountry)

        Assert.assertTrue(test.contains(fxtCountry.name!!))
    }

    @Test
    fun testDeserialization(){
        val adapter = moshi.adapter(CountryDto::class.java)

        val test = adapter.fromJson(SAMPLE) ?: error("Should have parsed correctly.")

        Assert.assertEquals("Afghanistan", test.name)
    }

    companion object {

        private val SAMPLE = """
            {
                    "name": "Afghanistan",
                    "topLevelDomain": [
                        ".af"
                    ],
                    "alpha2Code": "AF",
                    "alpha3Code": "AFG",
                    "callingCodes": [
                        "93"
                    ],
                    "capital": "Kabul",
                    "altSpellings": [
                        "AF",
                        "Afġānistān"
                    ],
                    "region": "Asia",
                    "subregion": "Southern Asia",
                    "population": 27657145,
                    "latlng": [
                        33.0,
                        65.0
                    ],
                    "demonym": "Afghan",
                    "area": 652230.0,
                    "gini": 27.8,
                    "timezones": [
                        "UTC+04:30"
                    ],
                    "borders": [
                        "IRN",
                        "PAK",
                        "TKM",
                        "UZB",
                        "TJK",
                        "CHN"
                    ],
                    "nativeName": "افغانستان",
                    "numericCode": "004",
                    "currencies": [
                        {
                            "code": "AFN",
                            "name": "Afghan afghani",
                            "symbol": "؋"
                        }
                    ],
                    "languages": [
                        {
                            "iso639_1": "ps",
                            "iso639_2": "pus",
                            "name": "Pashto",
                            "nativeName": "پښتو"
                        },
                        {
                            "iso639_1": "uz",
                            "iso639_2": "uzb",
                            "name": "Uzbek",
                            "nativeName": "Oʻzbek"
                        },
                        {
                            "iso639_1": "tk",
                            "iso639_2": "tuk",
                            "name": "Turkmen",
                            "nativeName": "Türkmen"
                        }
                    ],
                    "translations": {
                        "de": "Afghanistan",
                        "es": "Afganistán",
                        "fr": "Afghanistan",
                        "ja": "アフガニスタン",
                        "it": "Afghanistan",
                        "br": "Afeganistão",
                        "pt": "Afeganistão",
                        "nl": "Afghanistan",
                        "hr": "Afganistan",
                        "fa": "افغانستان"
                    },
                    "flag": "https://restcountries.eu/data/afg.svg",
                    "regionalBlocs": [
                        {
                            "acronym": "SAARC",
                            "name": "South Asian Association for Regional Cooperation",
                            "otherAcronyms": [],
                            "otherNames": []
                        }
                    ],
                    "cioc": "AFG"
                }
        """.trimIndent()
    }
}