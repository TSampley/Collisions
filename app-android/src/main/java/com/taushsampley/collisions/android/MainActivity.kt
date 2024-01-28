package com.taushsampley.collisions.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.taushsampley.collisions.ui.CollisionView
import com.taushsampley.collisions.ui.rememberCollisionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CollisionView(
                        state = rememberCollisionState(),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
