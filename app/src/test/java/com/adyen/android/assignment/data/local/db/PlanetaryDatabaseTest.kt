package com.adyen.android.assignment.data.local.db

import com.adyen.android.assignment.data.local.planetary.PlanetaryFavoriteDao
import com.adyen.android.assignment.testutils.RobolectricTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class PlanetaryDatabaseTest : RobolectricTest() {

    @MockK
    lateinit var database: PlanetaryDatabase

    @MockK
    lateinit var planetaryFavoriteDao: PlanetaryFavoriteDao



    @Test
    fun checkFavoritePlanetaryDao() {
        every { database.planetaryFavoriteDao() } returns planetaryFavoriteDao

        MatcherAssert.assertThat(
            database.planetaryFavoriteDao(),
            CoreMatchers.instanceOf(PlanetaryFavoriteDao::class.java)
        )
    }
}