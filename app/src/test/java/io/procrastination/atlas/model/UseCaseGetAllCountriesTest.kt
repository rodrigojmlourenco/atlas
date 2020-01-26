package io.procrastination.atlas.model

import com.flextrade.jfixture.JFixture
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.procrastination.atlas.data.AtlasService
import io.procrastination.atlas.helpers.FixtureHelper.fxtCountryDto
import io.procrastination.atlas.model.mappers.CountryMapper
import io.procrastination.atlas.model.usecases.UseCaseGetAllCountries
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UseCaseGetAllCountriesTest {

    private lateinit var sut: UseCaseGetAllCountries

    @MockK
    private lateinit var mockService: AtlasService

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = UseCaseGetAllCountries(
            mockService,
            CountryMapper()
        )
    }

    @Test
    fun `WHEN execute THEN provide list of countries`() {
        val fxtSample = (0..10).map { fxtCountryDto() }

        every { mockService.getAllCountries() } returns Single.just(fxtSample)

        sut().test()
            .assertComplete()
            .assertTerminated()
            .assertNoErrors()
            .assertValue { it.count() == fxtSample.count() }
    }


}