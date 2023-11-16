package com.example.demoshixian.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.demoshixian.model.GameAction
import com.example.demoshixian.model.GameState
import com.example.demoshixian.model.CellState
import com.example.demoshixian.model.ViewState

class GameViewModel : ViewModel() {
    //GameViewModel 点击状态分发

    var viewStates by mutableStateOf(ViewState())
        private set

    fun dispatch(action: GameAction) {
        when (action) {
            is GameAction.RunStep -> runStep()
            is GameAction.ToggleGameState -> toggleGameState()
            is GameAction.Clear -> clear()
            is GameAction.RandomGenerate -> randomGenerate(action.width, action.height, action.seed)
        }
    }

    private fun clear() {
        viewStates = viewStates.copy(gameState = GameState.Wait,
            cellState = CellState(CellState.randomGenerate(1, 1)))
    }

    private fun runStep() {
        val newList = viewStates.cellState.stepUpdate()
        viewStates = viewStates.copy(cellState = CellState(newList))
    }

    private fun toggleGameState() {
        viewStates = if (viewStates.gameState == GameState.Running) {
            viewStates.copy(gameState = GameState.Pause)
        } else {
            viewStates.copy(gameState = GameState.Running)
        }
    }

    private fun randomGenerate(width: Int, height: Int, seed: Long) {
        viewStates = viewStates.copy(gameState = GameState.Wait,
            cellState = CellState(CellState.randomGenerate(width, height, seed)))
    }
}