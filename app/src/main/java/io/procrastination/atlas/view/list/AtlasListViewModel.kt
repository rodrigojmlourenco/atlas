package io.procrastination.atlas.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.procrastination.atlas.model.AtlasScheduler
import io.procrastination.atlas.model.entities.Country
import io.procrastination.atlas.model.usecases.UseCaseGetAllCountries
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
        }
    }

    private fun orderCountriesAlphabetically() {
        Single.just(counties.value)
            .map { it.sortedBy { c -> c.name } }
            .subscribeOn(scheduler.subscribeOn)
            .observeOn(scheduler.observeOn)
            .subscribeBy(
                onSuccess = { response.postValue(Response.CountriesLoaded(it, true)) },
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
                onSuccess = { response.postValue(Response.CountriesLoaded(it, false)) },
                onError = { response.postValue(Response.Error(it.message ?: "")) }
            )
            .addTo(container)
    }

    sealed class Request {
        object LoadCountriesAlphabetically : Request()
        object LoadCountriesReverse : Request()
    }

    sealed class Response {
        data class Error(val message: String) : Response()

        data class CountriesLoaded(
            val countries: List<Country>,
            val alphabeticalOrder : Boolean
        ) : Response()
    }
}