package io.procrastination.atlas.model

import androidx.annotation.VisibleForTesting
import io.procrastination.atlas.data.AtlasService
import io.procrastination.atlas.data.CountryDto
import io.reactivex.Single
import javax.inject.Inject

class UseCaseGetAllCountries
@Inject constructor(private val service: AtlasService) : SingleUseCase<Void?, List<Country>>() {

    operator fun invoke(): Single<List<Country>> {
        return invoke(null)
    }

    override fun execute(params: Void?): Single<List<Country>> {
        return service.getAllCountries()
            .map { it.mapNotNull(this::mapDtoToEntity) }
    }

    @VisibleForTesting
    internal fun mapDtoToEntity(dto : CountryDto) : Country? {
        return Country(
            name = dto.name,
            capital = dto.capital,
            population = dto.population,
            latitude = dto.latlng[0],
            longitude = dto.latlng[1]
        )
    }
}