package com.adyen.android.assignment.presentation.planetary.list.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adyen.android.assignment.app.theme.DarkBlue
import com.adyen.android.assignment.data.model.dto.planetary.PlanetaryDto

@Composable
fun FavoritePlanetaryContent(
    favorPlanetaryList: List<PlanetaryDto>,
    onDetailItem: (String) -> Unit = {},
    onDeleteItem: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp),
    ) {
        itemsIndexed(favorPlanetaryList, key = { _, item -> item.date!! }) { _, favorPlanetary ->
            PlanetaryFavoriteRow(
                favorPlanetary = favorPlanetary,
                onDetailClick = {
                    onDetailItem.invoke(favorPlanetary.date!!)
                },
                onDeleteClick = {
                    onDeleteItem.invoke(favorPlanetary.date!!)
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlanetaryFavoriteRow(
    modifier: Modifier = Modifier,
    favorPlanetary: PlanetaryDto,
    onDetailClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Card(
        onClick = onDetailClick,
        modifier = modifier
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
                        .data(favorPlanetary.url)
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
                favorPlanetary.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 2.dp),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = favorPlanetary.date!!,
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                    )
                    IconButton(
                        onClick = onDeleteClick,
                        modifier = modifier
                            .width(34.dp)
                            .height(34.dp)
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Delete),
                            contentDescription = null,
                            tint = DarkBlue
                        )
                    }
                }
            }
        }
    }
}