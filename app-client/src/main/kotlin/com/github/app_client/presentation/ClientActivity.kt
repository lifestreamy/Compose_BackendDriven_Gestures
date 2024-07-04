package com.github.app_client.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.github.app_client.presentation.ui.MainRoute
import com.github.design_system.ui.theme.CommonAppTheme

class ClientActivity : ComponentActivity() {

    private val vm : ClientViewModel by viewModels<ClientViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CommonAppTheme {
                   MainRoute(vm = vm)
            }
        }
    }
}