package io.procrastination.atlas.view.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.procrastination.atlas.model.AtlasScheduler
import io.procrastination.atlas.model.entities.Country
import io.procrastination.atlas.model.error.CountryNotFoundException
import io.procrastination.atlas.model.usecases.UseCaseGetCountryByName
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import io.procrastination.atlas.view.details.CountryDetailsViewModel.Response

class CountryDetailsViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private val fixture = JFixture()

    private lateinit var sut: CountryDetailsViewModel

    @MockK
    private lateinit var mockGetCountryByNameUseCase: UseCaseGetCountryByName

    @MockK
    private lateinit var mockScheduler: AtlasScheduler

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { mockScheduler.subscribeOn } returns Schedulers.trampoline()
        every { mockScheduler.observeOn } returns Schedulers.trampoline()

        sut = CountryDetailsViewModel(mockScheduler, mockGetCountryByNameUseCase)
    }

    @Test
    fun `WHEN load country THEN has correct result`(){
        val fxtCountry = fixture.create(Country::class.java)
        every { mockGetCountryByNameUseCase(any()) } returns Single.just(fxtCountry)

        sut.loadCountry(fixture.create(String::class.java))

        Assert.assertTrue(sut.result.value is Response.CountryLoaded)
        (sut.result.value as? Response.CountryLoaded)?.let {
            Assert.assertEquals(fxtCountry, it.country)
        } ?: error("Unexpected response")
    }

    @Test
    fun `GIVEN country not found WHEN load country THEN fatal error`(){
        every { mockGetCountryByNameUseCase(any()) } returns Single.error(CountryNotFoundException(""))

        sut.loadCountry(fixture.create(String::class.java))

        Assert.assertTrue(sut.result.value is Response.FatalError)
    }

    @Test
    fun `GIVEN generic error WHEN load country THEN propagate error`(){
        every { mockGetCountryByNameUseCase(any()) } returns Single.error(Exception())

        sut.loadCountry(fixture.create(String::class.java))

        Assert.assertTrue(sut.result.value is Response.Error)
    }
}