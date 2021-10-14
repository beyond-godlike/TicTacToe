package com.unava.dia.tictactoe.data

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
        if (board[i].isEmpty()) {
            return false
        }
        // делаем ход (функция общая для компа и человека,
        // тк просто устанавливает значение и инкрементирует кво занятых клеток)
        move(i)
        return true
    }
    fun moveAi(): Int {
        // пытаемся выиграть
        var dest = bestMove(currPlayer!!)
        return if(dest != null) dest
        // блокируем выигрыш кожаного мешка
        else {
            dest = bestMove(players[0])
            if(dest != null) dest
            // если не можем выиграть или помешать победе ходим в рандомную клетку
            else {
                dest = autoPlay()
                // делаем ход
                moveComp(dest)
                dest
            }
        }
    }

    private fun move(i: Int) {
        board[i] = currPlayer!!
        filled++
    }

    fun moveComp(i: Int): Boolean {
        // если ячека занята выходим
        if (board[i].isNotEmpty()) {
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
        return board[i].isEmpty()
    }

    fun switchPlaya() {
        currPlayer = if (currPlayer === players[0]) players[1] else players[0]
    }

    fun isFieldFilled(): Boolean {
        return cells == filled
    }

    // наша доска по сути будет массивом из строк
    // "x" "o" "x" "o" x"" "o" "o" "x" "x"
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

    fun refreshBoard() {
        board = arrayListOf("", "", "",    "", "", "",    "", "", "")
        cells = 9
        filled = 0
    }
    fun start() {
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
        if (board[0] == currPlayer && board[1] == currPlayer && board[2] == currPlayer) board[0]
        else if (board[3] == currPlayer && board[4] == currPlayer && board[5] == currPlayer) board[3]
        else if (board[6] == currPlayer && board[7] == currPlayer && board[8] == currPlayer) board[6]

        // по вертикали
        else if (board[0] == currPlayer && board[3] == currPlayer && board[6] == currPlayer) board[0]
        else if (board[1] == currPlayer && board[4] == currPlayer && board[7] == currPlayer) board[1]
        else if (board[2] == currPlayer && board[5] == currPlayer && board[8] == currPlayer) board[2]
        // по диагонали
        else if (board[2] == currPlayer && board[4] == currPlayer && board[6] == currPlayer) board[2]
        else if (board[0] == currPlayer && board[4] == currPlayer && board[8] == currPlayer) board[0]
        return null
    }

    // вычисляет наилучший ход для игрока p
    private fun bestMove(p: String): Int? {
        // 00 01 02      0 1 2
        // 10 11 12      3 4 5
        // 20 21 22      6 7 8

        when {
            check(0, 1, 2, p) != null -> return check(0, 1, 2, p)
            check(0, 2, 1, p) != null -> return check(0, 2, 1, p)
            check(1, 2, 0, p) != null -> return check(1, 2, 0, p)

            check(3, 4, 5, p) != null -> return check(3, 4, 5, p)
            check(3, 5, 4, p) != null -> return check(3, 5, 4, p)
            check(4, 5, 3, p) != null -> return check(4, 5, 3, p)

            check(6, 7, 8, p) != null -> return check(6, 7, 8, p)
            check(6, 8, 7, p) != null -> return check(6, 8, 7, p)
            check(7, 8, 6, p) != null -> return check(7, 8, 6, p)


            // по вертикали
            check(3, 6, 0, p) != null -> return check(3, 6, 0, p)
            check(6, 0, 3, p) != null -> return check(6, 0, 3, p)
            check(0, 3, 6, p) != null -> return check(0, 3, 6, p)
            check(1, 4, 7, p) != null -> return check(1, 4, 7, p)
            check(1, 7, 4, p) != null -> return check(1, 7, 4, p)
            check(4, 7, 1, p) != null -> return check(4, 7, 1, p)
            check(2, 5, 8, p) != null -> return check(2, 5, 8, p)
            check(2, 8, 5, p) != null -> return check(2, 8, 5, p)
            check(5, 8, 2, p) != null -> return check(5, 8, 2, p)

            // по диагонали
            check(0, 4, 8, p) != null -> return check(0, 4, 8, p)
            check(4, 8, 0, p) != null -> return check(4, 8, 0, p)
            check(0, 8, 4, p) != null -> return check(0, 8, 4, p)
            check(2, 4, 6, p) != null -> return check(2, 4, 6, p)
            check(2, 6, 4, p) != null -> return check(2, 6, 4, p)
            check(4, 6, 2, p) != null -> return check(4, 6, 2, p)
        }
        return null
    }

    private fun check(
        x: Int,
        y: Int,
        dest: Int,
        p: String
    ): Int? {
        // если две клетки совпадают и у них значение игрока p
        if (board[x] == board[y] && board[x] == p
        ) {
            // если треться клетка не занята
            if (board[dest] != players[0] && isLegal(dest)) {
                // устанавливаем координаты для хода компа ( мы их потом передадим массивом в ui)
                // делаем ход
                move(dest)
                return dest
            }
        }
        return null
    }
}