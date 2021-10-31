package com.unava.dia.tictactoe.feature_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.unava.dia.tictactoe.ui.theme.TicTacToeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            TicTacToeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    val fState = remember {
                        viewModel.isFinished
                    }
                    val hasHandledNavigation = remember { mutableStateOf(false) }
                    if (fState) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("end")
                        }
                    }

                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        GameField(
                            board = viewModel.boardUi,
                            onClick = viewModel::move
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AppPreview() {
        TicTacToeTheme {
            //Scaffold(modifier = Modifier.fillMaxSize().background(Color.Cyan)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GameField(
                    board = arrayListOf("X", "O", "X", "O", "O", "X", "", "X", "O"),
                    Modifier.fillMaxSize()
                ) {}
            }
        }
    }
}