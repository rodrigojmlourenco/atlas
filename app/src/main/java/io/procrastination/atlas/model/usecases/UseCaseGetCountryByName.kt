package io.procrastination.atlas.model.usecases

import io.procrastination.atlas.data.AtlasService
import io.procrastination.atlas.model.entities.Country
import io.procrastination.atlas.model.error.CountryNotFoundException
import io.procrastination.atlas.model.mappers.CountryMapper
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UseCaseGetCountryByName
@Inject constructor(
    private val service: AtlasService,
    private val mapper: CountryMapper
) : SingleUseCase<String, Country>() {

    override fun execute(params: String): Single<Country> {
        return service.getAllCountries()
            .map { it.firstOrNull { it.name == params } ?: throw CountryNotFoundException(params) }
            .map { mapper.apply(it) ?: error("Unable to parse") }
            .doOnError { Timber.e(it) }
    }
}