package com.adyen.android.assignment.presentation.planetary.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.IndicatorHeight
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adyen.android.assignment.R
import com.adyen.android.assignment.app.views.EmptyView
import com.adyen.android.assignment.app.views.ErrorView
import com.adyen.android.assignment.app.views.LoadingView
import com.adyen.android.assignment.presentation.planetary.list.view.FavoritePlanetaryContent
import com.adyen.android.assignment.presentation.planetary.list.view.PlanetaryContent
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlanetaryScreen(
    modifier: Modifier = Modifier,
    viewModel: PlanetaryViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedIndex =
        rememberSaveable { mutableStateOf(PlanetaryTabs.PLANETARY_LIST.ordinal) }
    var openReorderDialog by remember {
        mutableStateOf(false)
    }


    PlanetaryBody(
        selectedTabIndex = selectedIndex.value,
        onOrder = {
            openReorderDialog = true
        }
    ) { padding ->
        Column {
            val tabsNames = remember { PlanetaryTabs.values().map { it.value } }
            TabRow(
                selectedTabIndex = selectedIndex.value,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        height = IndicatorHeight,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedIndex.value])
                    )
                }
            ) {
                tabsNames.forEachIndexed { index, stringResourceId ->
                    Tab(
                        selected = index == selectedIndex.value,
                        onClick = {
                            when (stringResourceId) {
                                PlanetaryTabs.PLANETARY_LIST.value -> {
                                    selectedIndex.value = PlanetaryTabs.PLANETARY_LIST.ordinal
                                }
                                PlanetaryTabs.PLANETARY_LIST_FAVORITE.value -> {
                                    selectedIndex.value =
                                        PlanetaryTabs.PLANETARY_LIST_FAVORITE.ordinal
                                }
                            }
                        },
                        text = {
                            Text(
                                text = stringResource(id = stringResourceId),
                            )
                        }
                    )
                }
            }
            when (selectedIndex.value) {
                PlanetaryTabs.PLANETARY_LIST.ordinal -> {
                    PlanetaryPage(uiState, viewModel, padding, navController, modifier)
                }
                PlanetaryTabs.PLANETARY_LIST_FAVORITE.ordinal -> {
                    FavoritesPage(uiState, viewModel, navController, modifier)
                }
            }
        }
        if (openReorderDialog) {
            ReorderDialog(onDismiss = {
                openReorderDialog = false
                viewModel.onTriggerEvent(
                    PlanetaryEvent.OrderPlanetary(
                        it,
                        (uiState as BaseViewState.Data<PlanetaryViewState>).value.planetaryList.mutableCopyOf()
                    )
                )
            })
        }

    }
}

fun <T> List<T>.mutableCopyOf(): MutableList<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}

private enum class PlanetaryTabs(@StringRes val value: Int) {
    PLANETARY_LIST(R.string.planetary_list),
    PLANETARY_LIST_FAVORITE(R.string.planetary_favorite_list);
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun PlanetaryPage(
    uiState: BaseViewState<*>,
    viewModel: PlanetaryViewModel,
    paddings: PaddingValues,
    navController: NavHostController,
    modifier: Modifier
) {
    val isData = uiState is BaseViewState.Data

    when (uiState) {
        is BaseViewState.Data -> PlanetaryContent(
            viewModel = viewModel,
            planetaryList = (uiState as BaseViewState.Data<PlanetaryViewState>).value.planetaryList,
            paddingValues = paddings,
            selectItem = { date -> navController.navigate("planetary/${date}/") }
        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.throwable,
            action = {
                viewModel.onTriggerEvent(PlanetaryEvent.LoadPlanetary)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        if ((isData && (uiState as BaseViewState.Data<PlanetaryViewState>).value.planetaryList.isEmpty()) || !isData) {
            viewModel.onTriggerEvent(PlanetaryEvent.LoadPlanetary)
        }
    })
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun FavoritesPage(
    uiState: BaseViewState<*>,
    viewModel: PlanetaryViewModel,
    navController: NavHostController,
    modifier: Modifier,
) {
    when (uiState) {
        is BaseViewState.Data -> FavoritePlanetaryContent(
            favorPlanetaryList = (uiState as BaseViewState.Data<PlanetaryViewState>).value.favorPlanetaryList,
            onDetailItem = { date -> navController.navigate("planetary/${date}/") },
            onDeleteItem = { date ->
                viewModel.onTriggerEvent(PlanetaryEvent.DeleteFavoritePlanetary(date))
            }

        )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            modifier = modifier,
            e = uiState.throwable,
            action = {
                viewModel.onTriggerEvent(PlanetaryEvent.LoadFavoritesPlanetary)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(PlanetaryEvent.LoadFavoritesPlanetary)
    })
}


@Composable
private fun PlanetaryBody(
    selectedTabIndex: Int,
    onOrder: () -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            PlanetaryToolbar(
                stringResource(R.string.planetary_list_title),
                elevation = 0.dp,
            )
        },
        floatingActionButton = {
            if (selectedTabIndex == PlanetaryTabs.PLANETARY_LIST.ordinal) {
                FloatingActionButton(
                    modifier = Modifier.padding(24.dp),
                    onClick = {
                        onOrder()
                    },
                    shape = RoundedCornerShape(percent = 50),
                ) {
                    Row(
                        modifier = Modifier.width(130.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(imageVector = Icons.Default.List, "")
                        Text(text = "Reorder list")
                    }
                }
            }
        },
        content = { pageContent.invoke(it) }
    )
}


@Composable
fun PlanetaryToolbar(
    title: String,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
) {
    TopAppBar(
        title = {
            Text(
                title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation
    )
}

@Composable
fun PlanetaryToolbarWithNavIcon(
    title: String,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { pressOnBack.invoke() }
            )
        },
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation
    )
}


@Composable
fun ReorderDialog(onDismiss: (reorderType: ReorderType) -> Unit) {
    var reorderType by remember {
        mutableStateOf(ReorderType.ORDER_BY_TITLE)
    }

    Dialog(
        onDismissRequest = {
            onDismiss(reorderType)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    text = "Reorder list",
                    textAlign = TextAlign.Center,
                )

                GroupedCheckbox(
                    mItemsList = listOf(
                        ReorderType.ORDER_BY_DATE,
                        ReorderType.ORDER_BY_TITLE,
                    ),
                    onSelect = {
                        reorderType = it
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF35898f)),
                    onClick = {
                        onDismiss(reorderType)
                    }) {
                    Text(
                        text = "Apply",
                        color = Color.White,
                    )
                }
                TextButton(
                    onClick = {
                        onDismiss(reorderType)
                    }) {
                    Text(
                        text = "Reset",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun GroupedCheckbox(mItemsList: List<ReorderType>, onSelect: (reorderType: ReorderType) -> Unit) {
    var selected by remember { mutableStateOf(ReorderType.ORDER_BY_TITLE) }
    mItemsList.forEach { item ->
        Row(
            modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = item.value)
            RadioButton(selected = (selected == item), onClick = {
                selected = item
                onSelect(item)
            })
        }
    }
}