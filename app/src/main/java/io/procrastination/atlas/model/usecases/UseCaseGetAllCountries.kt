package io.procrastination.atlas.model.usecases

import io.procrastination.atlas.data.AtlasService
import io.procrastination.atlas.model.entities.Country
import io.procrastination.atlas.model.mappers.CountryMapper
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UseCaseGetAllCountries
@Inject constructor(
    private val service: AtlasService,
    private val mapper: CountryMapper
) : SingleUseCase<Void?, List<Country>>() {

    operator fun invoke(): Single<List<Country>> {
        return invoke(null)
    }

    override fun execute(params: Void?): Single<List<Country>> {
        return service.getAllCountries()
            .map { mapper.apply(it).filterNotNull() }
            .doOnError { Timber.e(it) }
    }
}