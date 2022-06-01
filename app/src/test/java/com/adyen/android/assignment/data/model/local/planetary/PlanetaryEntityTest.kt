package com.adyen.android.assignment.data.model.local.planetary

import org.junit.Assert
import org.junit.Test

class PlanetaryEntityTest {

    @Test
    fun checkCorrectParams() {
        val date = "date"
        val copyright = "Michael Cain"
        val title = "Rocket Transits Rippling Sun"

        val entity = PlanetaryEntity(
            copyright = copyright,
            date = date,
            explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
            hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
            media_type = "image",
            service_version = "v1",
            title = title,
            url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
        )

        Assert.assertEquals(date, entity.date)
        Assert.assertEquals(copyright, entity.copyright)
        Assert.assertEquals(title, entity.title)
    }
}