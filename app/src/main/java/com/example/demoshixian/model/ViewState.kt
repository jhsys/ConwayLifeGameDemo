package com.example.demoshixian.model


//页面更新状态
data class ViewState(
    val gameState: GameState = GameState.Wait,
    val cellState: CellState = CellState(CellState.randomGenerate(200, 200))
)

enum class GameState(val msg: String) {
    Wait("Start"),
    Running("Pause"),
    Pause("Resume")
}

sealed class GameAction {
    object RunStep: GameAction()
    object ToggleGameState: GameAction()
    object Clear: GameAction()
    data class RandomGenerate(val width: Int, val height: Int, val seed: Long): GameAction()
}