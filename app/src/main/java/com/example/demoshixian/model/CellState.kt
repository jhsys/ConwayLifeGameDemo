package com.example.demoshixian.model

import android.graphics.Point

import com.example.demoshixian.data.Cell
import com.example.demoshixian.data.CellState
import kotlin.random.Random


data class CellState(
    val lifeList: List<List<Cell>>
) {

    //状态更新按照规则更新细胞状态
    fun stepUpdate(): List<List<Cell>> {
        val newLifeList: MutableList<List<Cell>> = mutableListOf()
        lifeList.forEach { lineList ->
            newLifeList.add(lineList.map { it.copy() })
        }

        newLifeList.forEachIndexed { columnIndex, lineList ->
            lineList.forEachIndexed { rowIndex, Cell ->
                val aroundAliveCount = getRoundAliveCount(Point(rowIndex, columnIndex))
                if (Cell.State.isAlive()) {
                    // 当前细胞存活
                    if (aroundAliveCount < 2) Cell.State = CellState.DEAD
                    if (aroundAliveCount > 3) Cell.State = CellState.DEAD
                } else {
                    // 当前细胞死亡
                    if (aroundAliveCount == 3) Cell.State = CellState.ALIVE
                }
            }
        }

        return newLifeList
    }

    //生命游戏的规则
    //    每个细胞有两种状态 - 存活或死亡，每个细胞与以自身为中心的周围八格细胞产生互动（如图，黑色为存活，白色为死亡）
    //    当前细胞为存活状态时，当周围的存活细胞低于2个时（不包含2个），该细胞变成死亡状态。（模拟生命数量稀少）
    //    当前细胞为存活状态时，当周围有2个或3个存活细胞时，该细胞保持原样。
    //    当前细胞为存活状态时，当周围有超过3个存活细胞时，该细胞变成死亡状态。（模拟生命数量过多）
    //    当前细胞为死亡状态时，当周围有3个存活细胞时，该细胞变成存活状态。（模拟繁殖）

    private fun getRoundAliveCount(pos: Point): Int {
        var count = 0
        if (pos.x > 0 && pos.y > 0 && lifeList[pos.y - 1][pos.x - 1].State.isAlive()) count++
        if (pos.y > 0 && lifeList[pos.y - 1][pos.x].State.isAlive()) count++
        if (pos.x < lifeList[0].lastIndex && pos.y > 0 && lifeList[pos.y - 1][pos.x + 1].State.isAlive()) count++
        if (pos.x > 0 && lifeList[pos.y][pos.x - 1].State.isAlive()) count++
        if (pos.x < lifeList[0].lastIndex && lifeList[pos.y][pos.x + 1].State.isAlive()) count++
        if (pos.x > 0 && pos.y < lifeList.lastIndex && lifeList[pos.y + 1][pos.x - 1].State.isAlive()) count++
        if (pos.y < lifeList.lastIndex && lifeList[pos.y + 1][pos.x].State.isAlive()) count++
        if (pos.x < lifeList[0].lastIndex && pos.y < lifeList.lastIndex && lifeList[pos.y + 1][pos.x + 1].State.isAlive()) count++

        return count
    }

    companion object {
        //初始化
        fun randomGenerate(
            width: Int,
            height: Int,
            seed: Long = System.currentTimeMillis()
        ): List<List<Cell>> {
            val list = mutableListOf<MutableList<Cell>>()
            val random = Random(seed)

            for (h in 0 until height) {
                val lineList = mutableListOf<Cell>()
                for (w in 0 until width) {
                    lineList.add(Cell(if (random.nextBoolean()) CellState.ALIVE else CellState.DEAD))
                }
                list.add(lineList)
            }

            return list
        }
    }
}