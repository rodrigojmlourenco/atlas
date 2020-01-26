package io.procrastination.atlas.model.mappers

import com.flextrade.jfixture.JFixture
import io.procrastination.atlas.helpers.FixtureHelper
import org.junit.Assert
import org.junit.Test

class CountryMapperTest {

    private val fixture = JFixture()

    private val sut : CountryMapper = CountryMapper()

    @Test
    fun `GIVEN sample WHEN map THEN has correct entity`() {
        val fxtLat = fixture.create(Double::class.java)
        val fxtLng = fixture.create(Double::class.java)
        val fxtSample = FixtureHelper.fxtCountryDto(latitude = fxtLat, longitude = fxtLng)

        val test = sut.apply(fxtSample) ?: error("Should not be null")

        Assert.assertNotNull(test)
        Assert.assertEquals(fxtSample.name, test.name)
        Assert.assertEquals(fxtSample.capital, test.capital)
        Assert.assertEquals(fxtSample.population, test.population)
        Assert.assertEquals(fxtLat, test.latitude, 0.toDouble())
        Assert.assertEquals(fxtLng, test.longitude, 0.toDouble())
    }
}