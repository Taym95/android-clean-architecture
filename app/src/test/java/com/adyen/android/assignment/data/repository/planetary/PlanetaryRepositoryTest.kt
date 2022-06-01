package com.adyen.android.assignment.data.repository.planetary

import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.data.model.local.planetary.PlanetaryEntity
import com.adyen.android.assignment.data.remote.service.PlanetaryService
import com.adyen.android.assignment.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class PlanetaryRepositoryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var planetaryService: PlanetaryService

    @MockK(relaxed = true)
    lateinit var dao: PlanetaryFavoriteDao

    private lateinit var repository: PlanetaryRepository

    override fun onCreate() {
        super.onCreate()
        repository = PlanetaryRepository(planetaryService, dao)
    }

    @Test
    fun getPlanetaryList() = runTest {
        val count = 1
        val apiKey = "api_key"

        repository.getPlanetaryList(
            count = count, apiKey = apiKey
        )

        coVerify {
            planetaryService.getPlanetaryList(
                count = count, apiKey = apiKey
            )
        }
    }

    @Test
    fun getPlanetary() = runTest {
        val planetaryDate = "2022-05-31"
        val apiKey = "api_key"

        repository.getPlanetary(
            date = planetaryDate, apiKey = apiKey
        )

        coVerify {
            planetaryService.getPlanetary(
                date = planetaryDate, apiKey = apiKey
            )
        }
    }

    @Test
    fun getFavoriteList() = runTest {
        repository.getFavoritePlanetaryList()

        coVerify { dao.getFavoriteList() }
    }

    @Test
    fun getFavoritePlanetary() = runTest {
        val favoriteDate = "2022-31-05"
        val favoriteDateCaptor = slot<String>()

        repository.getFavoritePlanetary(favoriteDate)

        coVerify { dao.getFavorite(favoriteDate = capture(favoriteDateCaptor)) }

        Assert.assertEquals(favoriteDate, favoriteDateCaptor.captured)
    }

    @Test
    fun deleteFavoritePlanetaryByDate() = runTest {
        val favoriteDate = "2022-31-05"
        val favoriteDateCaptor = slot<String>()
        repository.deleteFavoritePlanetaryByDate(favoriteDate)

        coVerify {
            dao.deleteFavoriteByDate(capture(favoriteDateCaptor))
        }
        Assert.assertEquals(favoriteDate, favoriteDateCaptor.captured)
    }

    @Test
    fun saveFavoritePlanetary() = runTest {
        val favoriteToInsert = PlanetaryEntity(
            copyright = "Michael Cain",
            date = "2022-05-31",
            explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
            hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
            media_type = "image",
            service_version = "v1",
            title = "Rocket Transits Rippling Sun",
            url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
        )

        val planetaryCaptor = slot<PlanetaryEntity>()
        repository.saveFavoritePlanetary(favoriteToInsert)

        coVerify { dao.insert(capture(planetaryCaptor)) }
        Assert.assertEquals(favoriteToInsert, planetaryCaptor.captured)
    }
}