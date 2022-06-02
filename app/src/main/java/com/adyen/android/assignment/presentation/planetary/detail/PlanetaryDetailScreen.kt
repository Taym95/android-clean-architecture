package com.adyen.android.assignment.presentation.planetary.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adyen.android.assignment.R
import com.adyen.android.assignment.app.views.EmptyView
import com.adyen.android.assignment.app.views.ErrorView
import com.adyen.android.assignment.app.views.LoadingView
import com.adyen.android.assignment.presentation.planetary.detail.view.PlanetaryDetailContent
import com.adyen.android.assignment.presentation.planetary.list.PlanetaryToolbar
import com.adyen.android.assignment.presentation.planetary.list.PlanetaryToolbarWithNavIcon
import com.adyen.android.assignment.utils.base.mvvm.BaseViewState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PlanetaryDetailScreen(
    date: String,
    viewModel: PlanetaryDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    PlanetaryDetailBody(pressOnBack = {
        navController.navigateUp()
    }) {
        when (uiState) {
            is BaseViewState.Data -> PlanetaryDetailContent(
                state = (uiState as BaseViewState.Data<PlanetaryDetailDetailViewState>).value,
                navController = navController
            )
            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = (uiState as BaseViewState.Error).throwable,
                action = {
                    viewModel.onTriggerEvent(PlanetaryDetailEvent.LoadPlanetaryDetail(date))
                }
            )
            is BaseViewState.Loading -> LoadingView()
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(PlanetaryDetailEvent.LoadPlanetaryDetail(date))
    })
}

@Composable
private fun PlanetaryDetailBody(
    pressOnBack: () -> Unit = {},
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            PlanetaryToolbarWithNavIcon(
                stringResource(R.string.planetary_details_title),
                pressOnBack = pressOnBack,
                elevation = 0.dp
            )
        },
        content = { pageContent.invoke(it) }
    )
}