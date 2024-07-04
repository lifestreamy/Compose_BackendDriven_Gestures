package com.github.app_server.presentation.ui

import androidx.compose.foundation.layout.*
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
import com.github.app_server.domain.datastore.model.ServerUserSettings
import com.github.app_server.presentation.ServerState
import com.github.app_server.presentation.ServerViewModel
import com.github.design_system.ui.theme.CommonAppTheme

@Composable
fun MainRoute(vm: ServerViewModel) {

    val clientState by vm.serverState.collectAsStateWithLifecycle()
    var showingSettingsDialog by rememberSaveable { mutableStateOf(false) }

    MainScreen(
        serverState = clientState,
        onSettingsClick = { showingSettingsDialog = true },
        onSettingsDismissed = { showingSettingsDialog = false },
        showingSettingsDialog = showingSettingsDialog
    )

}


@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    serverState: ServerState,
    onSettingsClick: () -> Unit,
    onSettingsDismissed: () -> Unit,
    showingSettingsDialog: Boolean = false
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                5.dp,
                Alignment.Top
            )
        ) {
            Text(
                text = "Server",
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
            serverState = ServerState(
                isTaskRunning = false,
                savedSettings = ServerUserSettings(
                    clientIp = null,
                    clientPort = null
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
            serverState = ServerState(
                isTaskRunning = false,
                savedSettings = ServerUserSettings(
                    clientIp = null,
                    clientPort = null
                )
            ),
            onSettingsClick = { -> },
            onSettingsDismissed = { -> },
            showingSettingsDialog = true
        )

    }
}