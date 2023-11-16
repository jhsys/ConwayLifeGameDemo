package com.example.demoshixian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoshixian.ui.theme.ConwayLifeGameTheme
import com.example.demoshixian.view.GameScreen
import com.example.demoshixian.viewModel.GameViewModel
import com.example.demoshixian.model.GameAction
import com.example.demoshixian.model.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class MainActivity : ComponentActivity() {
    companion object {
        const val AutoRunningDuration = 50L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConwayLifeGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: GameViewModel = viewModel()

                    LaunchedEffect(key1 = "game looper") {
                        while (isActive) {
                            if (viewModel.viewStates.gameState == GameState.Running) {
                                viewModel.dispatch(GameAction.RunStep)
                            }
                            delay(AutoRunningDuration)
                        }
                    }

                    GameScreen(viewModel = viewModel)
                }
            }
        }
    }
}