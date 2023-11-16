package com.example.demoshixian.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demoshixian.data.Cell

import com.example.demoshixian.model.CellState

@Composable
fun CellGrid(playGroundState: CellState) {
    val blockList: List<List<Cell>> = playGroundState.lifeList
    Canvas(modifier = Modifier
        .size((300.dp), 300.dp)
        .background(Color.Black)
    ) {
        blockList.forEachIndexed { Column, lineList ->
            lineList.forEachIndexed { row, cell ->
                if (cell.State.isAlive()) {
                    drawRect(color = cell.getColor(),
                        topLeft = Offset(row*Cell.SIZE.toDp().toPx(), Column*Cell.SIZE.toDp().toPx()),
                        size = Size(Cell.SIZE.toDp().toPx(), Cell.SIZE.toDp().toPx()))
                }
            }
        }
    }
}
