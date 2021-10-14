package com.unava.dia.tictactoe.feature_game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unava.dia.tictactoe.data.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val game: Game) : ViewModel() {
    init {
        game.init()
    }
    var boardUi by mutableStateOf(arrayListOf("", "", "", "", "", "", "", "", ""))
        private set

    fun move(id: Int) {
        // ходим в клетку, если она не занята
        if(game.isLegal(id)) {
            moveHuman(id)
            game.switchPlaya()
            // если игра закончилась выходим
            if(checkFinished()) return

            // если поле не заполнено и есть куда ходить ходит компьютер
            if(game.isFieldFilled()) {
                compMove()
                game.switchPlaya()
                checkFinished()
            }
        }
    }

    // проверка на конец игры
    private fun checkFinished(): Boolean {
        val winner: String? = game.isFinished()
        // если победитель null то проверяем на конец игры дальше а игра пока что не кончена
        if (winner != null) {
            //gameFinished(winner)
            return true
        }
        // если поле заполнено но нет победителя игра всеравно закончилась
        if (game.isFieldFilled()) {
            //gameFinished()
            return true
        }
        return false
    }

    private fun moveHuman(id: Int) {
        // установим в клетку значение игрока // update boardUi state
        game.moveHuman(id)
        boardUi[id] = game.currPlayer!!
    }

    private fun compMove() {
        // выставляем значения которыми походил компьютер
        // update boardUi state
        boardUi[game.moveAi()] = game.currPlayer!!
    }
}