package com.github.app_client.presentation.ui

import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.app_client.domain.datastore.model.ClientUserSettings
import com.github.app_client.presentation.ClientState
import com.github.app_client.presentation.ClientViewModel
import com.github.design_system.ui.theme.CommonAppTheme

@Composable
fun MainRoute(vm: ClientViewModel) {

    val clientState by vm.clientState.collectAsStateWithLifecycle()
    var showingSettingsDialog by rememberSaveable { mutableStateOf(false) }

    MainScreen(
        clientState = clientState,
        onSettingsClick = { showingSettingsDialog = true },
        onSettingsDismissed = { showingSettingsDialog = false },
        showingSettingsDialog = showingSettingsDialog
    )

}


@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    clientState: ClientState,
    onSettingsClick: () -> Unit,
    onSettingsDismissed: () -> Unit,
    showingSettingsDialog: Boolean = false
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .padding(innerPadding)
                .scrollable(
                    scrollState,
                    orientation = Orientation.Vertical,
                    flingBehavior = ScrollableDefaults.flingBehavior()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                5.dp,
                Alignment.Top
            )
        ) {
            Text(
                text = "Client",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            HorizontalDivider()
            IconButton(onClick = onSettingsClick){
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings"
                )
            }
            HorizontalDivider()

        }
    }
}


@Preview
@Composable
private fun MainScreenPrev1() {
    CommonAppTheme {
        MainScreen(
            clientState = ClientState(
                isTaskRunning = false,
                savedSettings = ClientUserSettings(
                    serverIP = null,
                    serverPort = null
                )
            ),
            onSettingsClick = { -> },
            onSettingsDismissed = { -> },
            showingSettingsDialog = false
        )

    }
}


@Preview
@Composable
private fun MainScreenPrev2() {
    CommonAppTheme {
        MainScreen(
            clientState = ClientState(
                isTaskRunning = false,
                savedSettings = ClientUserSettings(
                    serverIP = null,
                    serverPort = null
                )
            ),
            onSettingsClick = { -> },
            onSettingsDismissed = { -> },
            showingSettingsDialog = true
        )

    }
}