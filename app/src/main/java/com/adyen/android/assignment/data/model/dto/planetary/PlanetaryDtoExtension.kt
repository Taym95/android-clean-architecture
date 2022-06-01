package com.adyen.android.assignment.data.model.dto.planetary

import com.adyen.android.assignment.data.model.local.planetary.PlanetaryEntity
import com.adyen.android.assignment.data.model.remote.planetary.PlanetaryInfo


fun PlanetaryInfo.toPlanetaryDto() = PlanetaryDto(
    copyright = copyright,
    explanation = explanation,
    date = date,
    hdurl = hdurl,
    media_type = mediaType,
    service_version = serviceVersion,
    title = title,
    url = url,
)

fun List<PlanetaryInfo>.toPlanetaryDtoList() = map { it.toPlanetaryDto() }


fun PlanetaryEntity.toPlanetaryDto() = PlanetaryDto(
    copyright = copyright,
    explanation = explanation,
    date = date,
    hdurl = hdurl,
    media_type = media_type,
    service_version = service_version,
    title = title,
    url = url,
)

fun List<PlanetaryEntity>.toPlanetaryList() = map { it.toPlanetaryDto() }


fun PlanetaryDto.toPlanetaryEntity() = PlanetaryEntity(
    copyright = copyright.orEmpty(),
    explanation = explanation.orEmpty(),
    date = date.orEmpty(),
    hdurl = hdurl.orEmpty(),
    media_type = media_type.orEmpty(),
    service_version = service_version.orEmpty(),
    title = title.orEmpty(),
    url = url.orEmpty(),
)