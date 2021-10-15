package com.unava.dia.tictactoe.feature_game.data

import java.util.*
import kotlin.collections.ArrayList

class Game {
    private var players: ArrayList<String> = ArrayList()
    var currPlayer: String? = null
    private var filled = 0

    var board: ArrayList<String> = ArrayList()
    private var cells = 0
    fun moveHuman(i: Int): Boolean {
        // если клетка занята мы не можем ходить и выходим
        if (!isLegal(i)) {
            return false
        }
        // делаем ход (функция общая для компа и человека,
        // тк просто устанавливает значение и инкрементирует кво занятых клеток)
        move(i)
        return true
    }
    fun moveAi(): Int {
        // пытаемся выиграть
        var dest = bestMove(players[1])
        if (dest != null) {
            move(dest)
            return dest
        }
        // блокируем выигрыш кожаного мешка
        else {
            dest = bestMove(players[0])
            if (dest != null) {
                move(dest)
                return dest
            }
            // если не можем выиграть или помешать победе ходим в рандомную клетку
            else {
                dest = autoPlay()
                // делаем ход
                //moveComp(dest)
                move(dest)
                return dest
            }
        }
    }

    private fun move(i: Int) {
        board[i] = currPlayer!!
        filled++
    }

    private fun moveComp(i: Int): Boolean {
        // если ячека занята выходим
        if (!isLegal(i)) {
            return false
        }
        // делаем ход
        move(i)
        return true
    }

    private fun autoPlay(): Int {
        val rnd = Random()
        var coord = -1
        do {
            coord = rnd.nextInt(9)
        } while (!isLegal(coord))
        return coord
    }

    fun isLegal(i: Int): Boolean {
        return board[i] != "X" && board[i] != "O"
    }

    fun switchPlaya() {
        currPlayer = if (currPlayer == players[0]) players[1] else players[0]
    }

    fun isFieldFilled(): Boolean {
        return cells == filled
    }

    // очищаем все значения
    // стартуем игру
    fun restart() {
        refreshBoard()
        start()
    }

    fun init() {
        players.add("X")
        players.add("O")
        currPlayer = players[0]

        refreshBoard()
    }

    private fun refreshBoard() {
        board = arrayListOf("", "", "", "", "", "", "", "", "")
        cells = 9
        filled = 0
    }

    private fun start() {
        players[0] = "X"
        players[1] = "O"
        currPlayer = players[0]
    }

    fun isFinished(): String? {
        /// Win если на кнопках стоит X или O
        // 00 01 02      1 2 3
        // 10 11 12      4 5 6
        // 20 21 22      7 8 9

        // по горизонтали
        if (board[0] == board[1] && board[1] == board[2] && board[2] == currPlayer) return board[0]
        else if (board[3] == board[4] && board[4] == board[5] && board[3] == currPlayer) return board[3]
        else if (board[6] == board[7] && board[7] == board[8] && board[6] == currPlayer) return board[6]
        // по вертикали
        else if (board[0] == board[3] && board[3] == board[6] && board[0] == currPlayer) return board[0]
        else if (board[1] == board[4] && board[4] == board[7] && board[1] == currPlayer) return board[1]
        else if (board[2] == board[5] && board[5] == board[8] && board[2] == currPlayer) return board[2]
        // по диагонали
        else if (board[2] == board[4] && board[4] == board[6] && board[2] == currPlayer) return board[2]
        else if (board[0] == board[4] && board[4] == board[8] && board[0] == currPlayer) return board[0]
        return null
    }

    // вычисляет наилучший ход для игрока p
    private fun bestMove(p: String): Int? {
        // 00 01 02      0 1 2
        // 10 11 12      3 4 5
        // 20 21 22      6 7 8

        when {
            // по горизонтали
            check(0, 1, 2, p) -> return 2
            check(0, 2, 1, p) -> return 1
            check(1, 2, 0, p) -> return 0

            check(3, 4, 5, p) -> return 5
            check(4, 5, 3, p) -> return 3
            check(3, 5, 4, p) -> return 4

            check(6, 7, 8, p) -> return 8
            check(6, 8, 7, p) -> return 5
            check(7, 8, 6, p) -> return 2

            // по вертикали
            check(3, 6, 0, p) -> return 0
            check(6, 0, 3, p) -> return 3
            check(0, 3, 6, p) -> return 6

            check(1, 4, 7, p) -> return 7
            check(1, 7, 4, p) -> return 4
            check(4, 7, 1, p) -> return 1

            check(2, 5, 8, p) -> return 8
            check(2, 8, 5, p) -> return 5
            check(5, 8, 2, p) -> return 2

            // по диагонали
            check(0, 4, 8, p) -> return 8
            check(4, 8, 0, p) -> return 0
            check(0, 8, 4, p) -> return 4
            check(2, 4, 6, p) -> return 6
            check(2, 6, 4, p) -> return 4
            check(4, 6, 2, p) -> return 2
        }
        return null
    }

    private fun check(
        x: Int,
        y: Int,
        dest: Int,
        p: String,
    ): Boolean {
        // если две клетки совпадают и у них значение игрока p
        if (board[x] == board[y] && board[x] == p
        ) {
            // если треться клетка не занята
            if (isLegal(dest)) {
                // устанавливаем координаты для хода компа ( мы их потом передадим массивом в ui)
                // делаем ход
                //move(dest)
                return true
            }
        }
        return false
    }
}