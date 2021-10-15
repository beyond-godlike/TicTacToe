package com.unava.dia.tictactoe.feature_game

import android.os.Handler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unava.dia.tictactoe.feature_game.data.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(private val game: Game) : ViewModel() {
    init {
        game.init()
    }

    var boardUi by mutableStateOf(arrayListOf("", "", "", "", "", "", "", "", ""))
        private set

    var isFinished by mutableStateOf(false)
        private set

    var winner by mutableStateOf("")
        private set

    fun move(id: Int) {
        // ходим в клетку, если она не занята
        if (game.isLegal(id)) {
            moveHuman(id)
            // если игра закончилась выходим
            if (checkFinished()) return

            // если поле не заполнено и есть куда ходить ходит компьютер
            if (!game.isFieldFilled()) {
                compMove()
                checkFinished()
            }
        }
    }

    fun refresh() {
        isFinished = false
        boardUi = arrayListOf("", "", "", "", "", "", "", "", "")
        winner = ""
        game.restart()
    }

    private fun gameFinished(player: String) {
        // show toast
        newGame()
    }

    private fun newGame() {
        Handler().postDelayed({

            // начинаем игру заново
            game.restart()
            // обновляем пользовательский интерфейс
            refresh()
        }, 3000)
    }

    // проверка на конец игры
    private fun checkFinished(): Boolean {
        val winner: String? = game.isFinished()
        // если победитель null то проверяем на конец игры дальше а игра пока что не кончена
        if (winner == "X" || winner == "O") {
            gameFinished(winner)
            return true
        }
        // если поле заполнено но нет победителя игра всеравно закончилась
        if (game.isFieldFilled()) {
            gameFinished("")
            return true
        }
        return false
    }

    private fun moveHuman(id: Int) {
        // установим в клетку значение игрока // update boardUi state
        game.moveHuman(id)
        boardUi = ArrayList(boardUi.toMutableList().also {
            it[id] = "X"
        })
        game.switchPlaya()
    }

    private fun compMove() {
        // выставляем значения которыми походил компьютер
        // update boardUi state
        boardUi = ArrayList(boardUi.toMutableList().also {
            it[game.moveAi()] = "O"
        })
        game.switchPlaya()
    }
}