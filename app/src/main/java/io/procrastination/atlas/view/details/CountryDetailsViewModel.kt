package io.procrastination.atlas.view.details

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.procrastination.atlas.model.AtlasScheduler
import io.procrastination.atlas.model.entities.Country
import io.procrastination.atlas.model.error.CountryNotFoundException
import io.procrastination.atlas.model.usecases.UseCaseGetCountryByName
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CountryDetailsViewModel
@Inject constructor(
    private val scheduler: AtlasScheduler,
    private val getCountryByName: UseCaseGetCountryByName
) : ViewModel() {

    val result = MutableLiveData<Response>()

    @VisibleForTesting
    internal val container = CompositeDisposable()

    override fun onCleared() {
        container.dispose()
        super.onCleared()
    }

    fun loadCountry(name :String) {
        getCountryByName(name)
            .subscribeOn(scheduler.subscribeOn)
            .observeOn(scheduler.observeOn)
            .subscribeBy(
                onSuccess = { result.postValue(Response.CountryLoaded(it))},
                onError = { result.postValue(translateErrorToResponse(it))})
            .addTo(container)
    }

    private fun translateErrorToResponse(error :Throwable) : Response{
        return when(error){
            is CountryNotFoundException -> Response.FatalError
            else -> Response.Error("Something went wrong")
        }
    }

    sealed class Response {
        data class Error(val message : String) : Response()
        object FatalError : Response()
        data class CountryLoaded(val country: Country) : Response()
    }
}

