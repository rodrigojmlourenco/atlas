package io.procrastination.atlas.data

import io.reactivex.Single
import retrofit2.http.GET

interface AtlasService {

    @GET("all")
    fun getAllCountries() : Single<List<CountryDto>>
}