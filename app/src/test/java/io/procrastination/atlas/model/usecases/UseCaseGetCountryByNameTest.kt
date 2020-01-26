package io.procrastination.atlas.model.usecases

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.procrastination.atlas.data.AtlasService
import io.procrastination.atlas.helpers.FixtureHelper
import io.procrastination.atlas.model.error.CountryNotFoundException
import io.procrastination.atlas.model.mappers.CountryMapper
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UseCaseGetCountryByNameTest {

    private lateinit var sut : UseCaseGetCountryByName

    @MockK
    private lateinit var mockService : AtlasService

    @Before
    fun setup(){
        MockKAnnotations.init(this)

        sut = UseCaseGetCountryByName(mockService, CountryMapper())
    }

    @Test
    fun `GIVEN country exists WHEN execute THEN post country`(){
        val fxtSample = (0..10).map { FixtureHelper.fxtCountryDto() }
        val fxtTarget = fxtSample.first()
        every { mockService.getAllCountries() } returns Single.just(fxtSample)

        sut(fxtTarget.name!!).test()
            .assertComplete()
            .assertTerminated()
            .assertNoErrors()
            .assertValue { it.name == fxtTarget.name }
    }

    @Test
    fun `GIVEN country non-existant WHEN execute THEN error`(){
        val fxtSample = (0..10).map { FixtureHelper.fxtCountryDto() }
        val fxtTarget = FixtureHelper.fxtCountryDto()
        every { mockService.getAllCountries() } returns Single.just(fxtSample)

        sut(fxtTarget.name!!).test()
            .assertTerminated()
            .assertNotComplete()
            .assertError { it is CountryNotFoundException }
    }
}