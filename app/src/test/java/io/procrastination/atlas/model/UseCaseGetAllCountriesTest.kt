package io.procrastination.atlas.model

import com.flextrade.jfixture.JFixture
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.procrastination.atlas.data.AtlasService
import io.procrastination.atlas.data.CountryDto
import io.procrastination.atlas.helpers.FixtureHelper.fxtCountryDto
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UseCaseGetAllCountriesTest {

    private lateinit var sut: UseCaseGetAllCountries

    @MockK
    private lateinit var mockService: AtlasService

    private val fixture = JFixture()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = UseCaseGetAllCountries(mockService)
    }

    @Test
    fun `GIVEN sample WHEN map THEN has correct entity`() {
        val fxtLat = fixture.create(Double::class.java)
        val fxtLng = fixture.create(Double::class.java)
        val fxtSample = fxtCountryDto(latitude = fxtLat, longitude = fxtLng)

        val test = sut.mapDtoToEntity(fxtSample) ?: error("Should not be null")

        Assert.assertNotNull(test)
        Assert.assertEquals(fxtSample.name, test.name)
        Assert.assertEquals(fxtSample.capital, test.capital)
        Assert.assertEquals(fxtSample.population, test.population)
        Assert.assertEquals(fxtLat, test.latitude, 0.toDouble())
        Assert.assertEquals(fxtLng, test.longitude, 0.toDouble())
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