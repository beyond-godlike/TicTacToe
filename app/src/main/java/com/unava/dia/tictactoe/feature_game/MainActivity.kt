package com.unava.dia.tictactoe.feature_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.unava.dia.tictactoe.ui.theme.TicTacToeTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                Scaffold() {
                    Surface(color = MaterialTheme.colors.background) {
                        GameField(board = viewModel.boardUi, onClick = viewModel::move)
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AppPreview() {
        TicTacToeTheme {
            Scaffold() {
                Surface(color = MaterialTheme.colors.background) {
                    GameField(board = arrayListOf("X", "O", "X", "O", "O", "X", "", "X", "O")) {}
                }
            }
        }
    }
}