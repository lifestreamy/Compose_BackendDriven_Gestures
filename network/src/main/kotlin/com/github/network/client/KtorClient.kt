package com.github.network.client

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.github.network.model.Gesture
import com.github.network.model.GestureReport
import kotlinx.coroutines.flow.Flow

class KtorClient {

    var isStarted : Boolean = false
    var isConnected : Boolean = false

    lateinit var connectionState: Flow<ClientConnectionState>
        private set

    fun onConnectionStateChanged(newState : ClientConnectionState) {
        when(newState){
            ClientConnectionState.DISABLED -> {}
            ClientConnectionState.CONNECTING -> {}
            ClientConnectionState.CONNECTED -> {}
            ClientConnectionState.DISCONNECTED -> {}
        }
    }

//     fun startClient() : Boolean {
//
//     }
//
//
//     fun stopClient() : Boolean {
//
//     }


     fun processGesturesOnConnected(lambda : (Gesture) -> Unit) {

     }


//     fun sendGestureReport(report: GestureReport) : Boolean {
//
//     }

}