package com.example.demoshixian.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demoshixian.model.GameAction
import com.example.demoshixian.model.GameState
import androidx.compose.material.Button

import com.example.demoshixian.viewModel.GameViewModel

@Composable
fun ControlBar(viewModel: GameViewModel, gameState: GameState) {
    Row {
        Button(onClick = {
            viewModel.dispatch(GameAction.ToggleGameState)
        }) {
            Text(text = gameState.msg)
        }
        Button(
            modifier = Modifier.padding(start = 4.dp),
            enabled = gameState != GameState.Running,
            onClick = {
                viewModel.dispatch(GameAction.RunStep)
            }) {
            Text(text = "Step")
        }
//        Button(modifier = Modifier.padding(start = 4.dp), onClick = {
//            viewModel.dispatch(GameAction.Clear)
//        }) {
//            Text(text = "Clear")
//        }
//        Button(modifier = Modifier.padding(start = 4.dp), onClick = {
//            // 先写死，可以修改
//            viewModel.dispatch(GameAction.RandomGenerate(50, 50, System.currentTimeMillis()))
//        }) {
//            Text(text = "Random")
//        }
    }
}
