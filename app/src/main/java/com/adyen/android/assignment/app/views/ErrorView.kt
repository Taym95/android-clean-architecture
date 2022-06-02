package com.adyen.android.assignment.app.views

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import com.adyen.android.assignment.R
import com.adyen.android.assignment.utils.network.ConnectionState
import com.adyen.android.assignment.utils.network.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ErrorView(modifier: Modifier = Modifier, e: Throwable, action: () -> Unit) {
    e.printStackTrace()
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        val mContext = LocalContext.current
        val connection by connectivityState()
        val isConnected = connection === ConnectionState.Available

        Icon(
            painter = rememberVectorPainter(Icons.Default.ErrorOutline),
            contentDescription = null,
            tint = Red,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = Error(isConnected = isConnected, error = e),
            textAlign = TextAlign.Center
        )
        Button(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            onClick = {
                if (isConnected) {
                    action()
                } else {
                    startActivity(mContext, Intent(Settings.ACTION_SETTINGS), null)
                }
            }
        ) {
            Text(text = ErrorButton(isConnected = isConnected))
        }
    }
}

@Composable
private fun Error(isConnected: Boolean, error: Throwable): String {
    return if (isConnected) {
        stringResource(id = R.string.something_went_wrong) + "\n" + stringResource(id = R.string.something_went_wrong_helper) + "\n" + error.localizedMessage
    } else {
        stringResource(id = R.string.no_internet) + "\n" + stringResource(id = R.string.no_internet_helper) + "\n" + error.localizedMessage
    }
}

@Composable
private fun ErrorButton(isConnected: Boolean): String {
    return if (isConnected) {
        stringResource(id = R.string.refresh)
    } else {
        stringResource(id = R.string.network_setting)
    }
}