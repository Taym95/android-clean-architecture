package com.adyen.android.assignment.data.local.dao

import androidx.room.Room
import com.adyen.android.assignment.data.local.db.PlanetaryDatabase
import com.adyen.android.assignment.data.local.mockdata.LocalMockData
import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.data.model.local.planetary.PlanetaryEntity
import com.adyen.android.assignment.testutils.RobolectricTest
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import java.io.IOException


class PlanetaryFavoriteDaoTest : RobolectricTest() {
    private lateinit var database: PlanetaryDatabase

    private lateinit var dao: PlanetaryFavoriteDao

    override fun onCreate() {
        super.onCreate()
        runTest {
            database = Room
                .inMemoryDatabaseBuilder(context, PlanetaryDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            dao = database.planetaryFavoriteDao()
        }
    }

    @Throws(IOException::class)
    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }

    @Test
    fun getFavoritePlanetaryList_WithData() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        dao.insert(fakeFavoritePlanetary)
        val favorites = dao.getFavoriteList()
        Assert.assertEquals(fakeFavoritePlanetary, favorites)
    }

    @Test
    fun getFavoritePlanetaryList_WithoutData() = runTest {
        val favoritesPlanetary = dao.getFavoriteList()
        Assert.assertTrue(favoritesPlanetary.isNullOrEmpty())
    }

    @Test
    fun getFavoritePlanetaryByDate_WithoutData_ShouldNotFound() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        val favoritePlanetaryToFind = fakeFavoritePlanetary.first()
        Assert.assertNull(dao.getFavorite(favoritePlanetaryToFind.date))
    }

    @Test
    fun getFavoritePlanetaryByDate_WithData_ShouldFound() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        dao.insert(fakeFavoritePlanetary)
        val favoritePlanetaryToFind = fakeFavoritePlanetary.first()
        Assert.assertEquals(favoritePlanetaryToFind, dao.getFavorite(favoritePlanetaryToFind.date))
    }

    @Test
    fun insertFavoritePlanetary_ShouldAdd() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        fakeFavoritePlanetary.forEach { dao.insert(it) }

        Assert.assertEquals(fakeFavoritePlanetary, dao.getFavoriteList())
    }

    @Test
    fun deleteFavoritePlanetaryList_ShouldRemoveAll() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        dao.insert(fakeFavoritePlanetary)
        dao.deleteFavoriteList()

        Assert.assertTrue(dao.getFavoriteList().isNullOrEmpty())
    }

    @Test
    fun deleteFavoritePlanetary_Stored_ShouldRemoveIt() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        dao.insert(fakeFavoritePlanetary)

        val favoritePlanetaryToRemove = fakeFavoritePlanetary.first()
        dao.delete(favoritePlanetaryToRemove)

        MatcherAssert.assertThat(
            dao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoritePlanetaryToRemove))
        )
    }

    @Test
    fun deleteFavoritePlanetary_NoStored_ShouldNotRemoveNothing() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        dao.insert(fakeFavoritePlanetary)

        val favoritePlanetaryToRemove = PlanetaryEntity(
            copyright = "Michael Cain",
            date = "2022-05-30",
            explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
            hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
            media_type = "image",
            service_version = "v1",
            title = "Rocket Transits Rippling Sun",
            url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
        )
        dao.delete(favoritePlanetaryToRemove)

        Assert.assertEquals(fakeFavoritePlanetary, dao.getFavoriteList())
    }

    @Test
    fun deleteFavoritePlanetaryByDate_Stored_ShouldRemoveIt() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        dao.insert(fakeFavoritePlanetary)

        val favoritePlanetaryToRemove = fakeFavoritePlanetary.first()
        dao.deleteFavoriteByDate(favoritePlanetaryToRemove.date)

        MatcherAssert.assertThat(
            dao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoritePlanetaryToRemove))
        )
    }

    @Test
    fun deleteFavoritePlanetaryByDate_NoStored_ShouldNotRemoveNothing() = runTest {
        val fakeFavoritePlanetary = LocalMockData.getFavoritePlanetaryList()
        dao.insert(fakeFavoritePlanetary)

        val favoritePlanetaryNoStoreddate = "date"
        dao.deleteFavoriteByDate(favoritePlanetaryNoStoreddate)

        Assert.assertEquals(fakeFavoritePlanetary, dao.getFavoriteList())
    }
}