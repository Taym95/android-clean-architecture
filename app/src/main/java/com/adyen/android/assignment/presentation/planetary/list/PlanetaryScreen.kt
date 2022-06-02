package com.adyen.android.assignment.presentation.planetary.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.IndicatorHeight
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adyen.android.assignment.R
import com.adyen.android.assignment.app.views.EmptyView
import com.adyen.android.assignment.app.views.ErrorView
import com.adyen.android.assignment.app.views.LoadingView
import com.adyen.android.assignment.presentation.planetary.list.view.FavoritePlanetaryContent
import com.adyen.android.assignment.presentation.planetary.list.view.PlanetaryContent
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState
import com.adyen.android.assignment.utils.network.ConnectionState
import com.adyen.android.assignment.utils.network.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@Composable
fun PlanetaryScreen(
    modifier: Modifier = Modifier,
    viewModel: PlanetaryViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    PlanetaryBody(
    ) { padding ->
        Column {
            val tabsNames = remember { PlanetaryTabs.values().map { it.value } }
            val selectedIndex =
                rememberSaveable { mutableStateOf(PlanetaryTabs.PLANETARY_LIST.ordinal) }
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
    }
}

private enum class PlanetaryTabs(@StringRes val value: Int) {
    PLANETARY_LIST(R.string.planetary_list),
    PLANETARY_LIST_FAVORITE(R.string.planetary_favorite_list);
}

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
            if(isData && (uiState as BaseViewState.Data<PlanetaryViewState>).value.planetaryList.isEmpty()){
                viewModel.onTriggerEvent(PlanetaryEvent.LoadPlanetary)
            } else if(!isData){
                viewModel.onTriggerEvent(PlanetaryEvent.LoadPlanetary)
            }
        })
}


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
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            PlanetaryToolbar(
                stringResource(R.string.planetary_list_title),
                elevation = 0.dp,
            )
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
