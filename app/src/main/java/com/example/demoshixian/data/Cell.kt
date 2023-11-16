package com.example.demoshixian.data

import androidx.compose.ui.graphics.Color

//格子的尺寸
data class Cell(var State: CellState) {
    fun getColor() = if (this.State.isAlive()) Color.White else Color.Black

    companion object {
        const val SIZE = 10
    }
}

//细胞的状态 存活或死亡
enum class CellState {
    ALIVE,
    DEAD;

    fun isAlive() = this == ALIVE
}