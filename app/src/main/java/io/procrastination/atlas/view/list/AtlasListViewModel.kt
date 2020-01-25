package io.procrastination.atlas.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.procrastination.atlas.model.AtlasScheduler
import io.procrastination.atlas.model.Country
import io.procrastination.atlas.model.UseCaseGetAllCountries
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class AtlasListViewModel
@Inject constructor(
    private val scheduler: AtlasScheduler,
    getCountriesUseCase: UseCaseGetAllCountries
) : ViewModel() {

    val counties = MutableLiveData<List<Country>>()
    val response = MutableLiveData<Response>()

    private val container = CompositeDisposable()

    init {
        getCountriesUseCase()
            .subscribeOn(scheduler.subscribeOn)
            .observeOn(scheduler.io)
            .subscribeBy(
                onSuccess = {
                    counties.postValue(it)
                },
                onError = {
                    response.postValue(Response.Error("Unable to load the countries"))
                }
            )
            .addTo(container)
    }

    fun handleRequest(request: Request) {
        when (request) {
            is Request.LoadCountriesAlphabetically -> orderCountriesAlphabetically()

            is Request.LoadCountriesReverse -> orderCountriesReversed()

            is Request.GetCountry -> {

                val r = counties.value?.firstOrNull { it.name == request.name }?.let {
                    Response.CountrySelected(it)
                } ?: Response.Error("Country not found.")


                response.postValue(r)
            }
        }
    }

    private fun orderCountriesAlphabetically() {
        Single.just(counties.value)
            .map { it.sortedBy { c -> c.name } }
            .subscribeOn(scheduler.subscribeOn)
            .observeOn(scheduler.observeOn)
            .subscribeBy(
                onSuccess = { response.postValue(Response.CountriesLoaded(it)) },
                onError = { response.postValue(Response.Error(it.message ?: "")) }
            )
            .addTo(container)
    }

    private fun orderCountriesReversed() {
        Single.just(counties.value)
            .map { it.sortedByDescending { c -> c.name } }
            .subscribeOn(scheduler.subscribeOn)
            .observeOn(scheduler.observeOn)
            .subscribeBy(
                onSuccess = { response.postValue(Response.CountriesLoaded(it)) },
                onError = { response.postValue(Response.Error(it.message ?: "")) }
            )
            .addTo(container)
    }

    sealed class Request {
        object LoadCountriesAlphabetically : Request()
        object LoadCountriesReverse : Request()
        data class GetCountry(val name : String) : Request()
    }

    sealed class Response {
        data class Error(val message: String) : Response()
        data class CountriesLoaded(val countries: List<Country>) : Response()
        data class CountrySelected(val country: Country) : Response()
    }
}