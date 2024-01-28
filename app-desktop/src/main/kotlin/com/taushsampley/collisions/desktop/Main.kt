package com.taushsampley.collisions.desktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.taushsampley.collisions.ui.CollisionView
import com.taushsampley.collisions.ui.rememberCollisionState

/**
 *
 */
fun main() = application {

    Window(
        onCloseRequest = this::exitApplication,
        title = "",
        state = rememberWindowState()
    ) {
        CollisionView(
            state = rememberCollisionState(),
            modifier = Modifier.fillMaxSize()
        )
    }
}
