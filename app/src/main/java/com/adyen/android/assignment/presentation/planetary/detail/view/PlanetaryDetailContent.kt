package com.adyen.android.assignment.presentation.planetary.detail.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.presentation.planetary.detail.PlanetaryDetailDetailViewState
import com.adyen.android.assignment.R

@Composable
fun PlanetaryDetailContent(
    state: PlanetaryDetailDetailViewState,
    navController: NavHostController
) {
    LazyColumn {
        state.planetaryDto?.let { planetary ->
            item("header") {
                PlanetaryDetailHeaderView(planetary = planetary)
            }
            item("info") {
                PlanetaryDetailInfoView(planetary = planetary)
            }
        }
    }
}


@Composable
fun PlanetaryDetailHeaderView(planetary: PlanetaryDto?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .width(400.dp)
            .padding(top = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(planetary?.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(size = 16.dp))
        )
    }
}

@Composable
fun PlanetaryDetailInfoView(planetary: PlanetaryDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.planetary_details_info),
            modifier = Modifier
                .padding(12.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                planetary.title?.let {
                    ColumnItem(
                        key = stringResource(id = R.string.planetary_details_info_title),
                        value = it
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                )

                ColumnItem(
                    key = stringResource(id = R.string.planetary_details_info_date),
                    value = planetary.date!!
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                )

                planetary.copyright?.let {
                    ColumnItem(
                        key = stringResource(id = R.string.planetary_details_info_copyright),
                        value = it
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                )
            }
        }
        Text(
            text = stringResource(id = R.string.planetary_details_explanation),
            modifier = Modifier
                .padding(12.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            planetary.explanation?.let { Text(text = it) }

        }
    }
}


@Composable
fun ColumnItem(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = key,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Start
        )
        Text(
            text = value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )
    }
}
