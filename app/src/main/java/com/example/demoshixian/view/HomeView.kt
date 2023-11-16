package com.example.demoshixian.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.demoshixian.viewModel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            CellGrid(playGroundState = viewModel.viewStates.cellState)
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            ControlBar(viewModel, gameState = viewModel.viewStates.gameState)
        }
    }
}
