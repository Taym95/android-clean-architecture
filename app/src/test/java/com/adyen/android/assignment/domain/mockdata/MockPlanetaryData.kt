package com.adyen.android.assignment.domain.mockdata

import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto

object MockPlanetaryData {

    fun getPlanetaryDto(): PlanetaryDto {
        return PlanetaryDto(
            copyright = "Michael Cain",
            date = "2022-05-31",
            explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
            hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
            media_type = "image",
            service_version = "v1",
            title = "Rocket Transits Rippling Sun",
            url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
        )
    }

    fun getPlanetaryListDto(): List<PlanetaryDto> {
        return listOf(
            PlanetaryDto(
                copyright = "Bob",
                date = "2022-05-31",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 2",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
            PlanetaryDto(
                copyright = "Tim",
                date = "2022-07-02",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 1",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
            PlanetaryDto(
                copyright = "Zizo",
                date = "2022-06-01",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 3",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
        )
    }


    fun getPlanetaryOrderByDateListDto(): List<PlanetaryDto> {
        return listOf(
            PlanetaryDto(
                copyright = "Tim",
                date = "2022-07-02",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 1",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
            PlanetaryDto(
                copyright = "Zizo",
                date = "2022-06-01",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 3",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
            PlanetaryDto(
                copyright = "Bob",
                date = "2022-05-31",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 2",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
        )
    }

    fun getPlanetaryOrderByTitleListDto(): List<PlanetaryDto> {
        return listOf(
            PlanetaryDto(
                copyright = "Tim",
                date = "2022-07-02",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 1",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
            PlanetaryDto(
                copyright = "Bob",
                date = "2022-05-31",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 2",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
            PlanetaryDto(
                copyright = "Zizo",
                date = "2022-06-01",
                explanation = "The launch of a rocket at sunrise can result in unusual but intriguing images that feature both the rocket and the Sun. Such was the case last month when a SpaceX Falcon 9 rocket blasted off from NASA's Kennedy Space Center carrying 53 more Starlink satellites into low Earth orbit. In the featured launch picture, the rocket's exhaust plume glows beyond its projection onto the distant Sun, the rocket itself appears oddly jagged, and the Sun's lower edge shows peculiar drip-like ripples. The physical cause of all of these effects is pockets of relatively hot or rarefied air deflecting sunlight less strongly than pockets relatively cool or compressed air: refraction.  Unaware of the Earthly show, active sunspot region 3014 -- on the upper left -- slowly crosses the Sun.",
                hdurl = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_2166.jpg",
                media_type = "image",
                service_version = "v1",
                title = "title 3",
                url = "https://apod.nasa.gov/apod/image/2205/FalconSun_Cain_960.jpg"
            ),
        )
    }

}