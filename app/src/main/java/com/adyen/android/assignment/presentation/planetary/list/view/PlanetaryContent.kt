package com.adyen.android.assignment.presentation.planetary.list.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto
import com.adyen.android.assignment.presentation.planetary.list.PlanetaryEvent
import com.adyen.android.assignment.presentation.planetary.list.PlanetaryViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import com.adyen.android.assignment.app.theme.PlanetaryTypography
import com.adyen.android.assignment.app.theme.DarkBlue


@Composable
fun PlanetaryContent(
    viewModel: PlanetaryViewModel = hiltViewModel(),
    planetaryList: List<PlanetaryDto>,
    paddingValues: PaddingValues,
    selectItem: (String) -> Unit = {}
) {

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp),
    ) {
        items(planetaryList.size) { index ->
            planetaryList[index].let {
                PlanetaryRow(
                    viewModel,
                    planetary = it,
                    onDetailClick = { selectItem.invoke(it.date!!) }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlanetaryRow(
    viewModel: PlanetaryViewModel = hiltViewModel(),
    planetary: PlanetaryDto,
    onDetailClick: () -> Unit = {}
) {
    Card(
        onClick = onDetailClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ),
        elevation = 8.dp
    ) {
        Row {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(planetary.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(size = 8.dp))
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 4.dp, bottom = 4.dp)
            ) {
                planetary.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 2.dp),
                        style = PlanetaryTypography.subtitle1
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = planetary.date!!,
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        style = PlanetaryTypography.subtitle1
                    )
                    FavoriteButton(viewModel, planetary)
                }
            }
        }
    }
}

@Composable
fun FavoriteButton(
    viewModel: PlanetaryViewModel = hiltViewModel(),
    planetary: PlanetaryDto,
) {
    var isFavorite by rememberSaveable(planetary) { mutableStateOf(planetary.isFavorite) }

    IconButton(onClick = {
        isFavorite = !isFavorite
        viewModel.onTriggerEvent(PlanetaryEvent.AddFavoritePlanetary(planetary))
    }) {
        val tintColor = if (isFavorite) DarkBlue else Color(0xFF6E6E6E)
        Icon(
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = null,
            tint = tintColor
        )
    }
}