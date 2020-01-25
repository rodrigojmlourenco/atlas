package io.procrastination.atlas.view.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.procrastination.atlas.model.AtlasScheduler
import io.procrastination.atlas.model.Country
import io.procrastination.atlas.model.UseCaseGetAllCountries
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import io.procrastination.atlas.view.list.AtlasListViewModel.Request
import io.procrastination.atlas.view.list.AtlasListViewModel.Response

class AtlasListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var sut: AtlasListViewModel

    @MockK
    private lateinit var mockScheduler: AtlasScheduler

    @MockK
    private lateinit var mockGetCountriesUseCase: UseCaseGetAllCountries

    private val fixture = JFixture()

    private val fxtSample = (0..10).map { fixture.create(Country::class.java) }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { mockScheduler.io } returns Schedulers.trampoline()
        every { mockScheduler.observeOn } returns Schedulers.trampoline()
        every { mockScheduler.subscribeOn } returns Schedulers.trampoline()

        every { mockGetCountriesUseCase() } returns Single.just(fxtSample)

        sut = AtlasListViewModel(mockScheduler, mockGetCountriesUseCase)
    }

    @Test
    fun `WHEN created THEN has countries`(){
        Assert.assertEquals(fxtSample, sut.counties.value)
    }

    @Test
    fun `WHEN order alphabetically THEN correct answer`(){
        sut.handleRequest(Request.LoadCountriesAlphabetically)

        Assert.assertTrue(sut.response.value is Response.CountriesLoaded)

        (sut.response.value as Response.CountriesLoaded).let {
            Assert.assertEquals(fxtSample.sortedBy { it.name }, it.countries)
        }
    }

    @Test
    fun `WHEN order reversed THEN correct answer`(){
        sut.handleRequest(Request.LoadCountriesReverse)

        Assert.assertTrue(sut.response.value is Response.CountriesLoaded)

        (sut.response.value as Response.CountriesLoaded).let {
            Assert.assertEquals(fxtSample.sortedByDescending { it.name }, it.countries)
        }
    }

    @Test
    fun `WHEN get country THEN correct answer`(){
        val fxtName = fxtSample.first().name

        sut.handleRequest(Request.GetCountry(fxtName))

        Assert.assertTrue(sut.response.value is Response.CountrySelected)
        (sut.response.value as Response.CountrySelected).let {
            Assert.assertEquals(fxtSample.first(), it.country)
        }
    }

    @Test
    fun `GIVEN country does not exit WHEN get country THEN error`(){
        val fxtName = fixture.create(String::class.java)

        sut.handleRequest(Request.GetCountry(fxtName))

        Assert.assertTrue(sut.response.value is Response.Error)
    }
}