package io.procrastination.atlas.model.entities

data class Country(
    val name : String,
    val capital: String,
    val population: Int,
    val latitude: Double,
    val longitude: Double
)