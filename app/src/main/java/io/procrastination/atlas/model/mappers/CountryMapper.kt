package io.procrastination.atlas.model.mappers

import io.procrastination.atlas.data.CountryDto
import io.procrastination.atlas.model.entities.Country
import io.reactivex.functions.Function
import timber.log.Timber
import javax.inject.Inject

class CountryMapper
@Inject constructor() : Function<CountryDto, Country?> {

    override fun apply(dto: CountryDto): Country? {
        return try {
            Country(
                name = dto.name ?: error("Missing name for $dto"),
                capital = dto.capital ?: error("Missing capital for $dto"),
                population = dto.population ?: error("Missing population for $dto"),
                latitude = dto.latlng?.get(0) ?: error("Missing coordinate system for $dto"),
                longitude = dto.latlng[1]
            )
        } catch (e: IllegalArgumentException) {
            null
        } catch (e: IndexOutOfBoundsException) {
            Timber.e(e, "Invalid lat and lng for $dto")
            null
        }
    }

    fun apply(dto: List<CountryDto>): List<Country?> {
        return dto.map { apply(it) }
    }
}