package com.unava.dia.tictactoe.feature_game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unava.dia.tictactoe.ui.theme.Purple500


@Composable
fun GameField(board: ArrayList<String>, onClick: (Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            GameButton(text = board[0]) { onClick(0) }
            GameButton(text = board[1]) { onClick(1) }
            GameButton(text = board[2]) { onClick(2) }
        }
        Row(horizontalArrangement = Arrangement.Center) {
            GameButton(text = board[3]) { onClick(3) }
            GameButton(text = board[4]) { onClick(4) }
            GameButton(text = board[5]) { onClick(5) }
        }
        Row(horizontalArrangement = Arrangement.Center) {
            GameButton(text = board[6]) { onClick(6) }
            GameButton(text = board[7]) { onClick(7) }
            GameButton(text = board[8]) { onClick(8) }
        }
    }
}

@Composable
fun GameButton(text: String, onclick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        TextButton(
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Purple500),
            onClick = onclick,
            enabled = text.isBlank()
        ) {
            Text(
                text = text,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}