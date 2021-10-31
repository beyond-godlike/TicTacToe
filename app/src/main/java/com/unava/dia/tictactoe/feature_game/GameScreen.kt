package com.unava.dia.tictactoe.feature_game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.unava.dia.tictactoe.ui.theme.Blue2
import com.unava.dia.tictactoe.ui.theme.BlueStroke


@Composable
fun GameField(
    board: ArrayList<String>,
    modifier: Modifier = Modifier.fillMaxSize(),
    onClick: (Int) -> Unit,
) {
    val constraints = ConstraintSet {
        val bt1 = createRefFor("bt1")
        val bt2 = createRefFor("bt2")
        val bt3 = createRefFor("bt3")

        val bt4 = createRefFor("bt4")
        val bt5 = createRefFor("bt5")
        val bt6 = createRefFor("bt6")

        val bt7 = createRefFor("bt7")
        val bt8 = createRefFor("bt8")
        val bt9 = createRefFor("bt9")

        constrain(bt1) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        constrain(bt2) {
            top.linkTo(parent.top)
            start.linkTo(bt1.end)
        }
        constrain(bt3) {
            top.linkTo(parent.top)
            start.linkTo(bt2.end)
        }

        constrain(bt4) {
            top.linkTo(bt1.bottom)
            start.linkTo(parent.start)
        }
        constrain(bt5) {
            top.linkTo(bt2.bottom)
            start.linkTo(bt4.end)
        }
        constrain(bt6) {
            top.linkTo(bt3.bottom)
            start.linkTo(bt5.end)
        }

        constrain(bt7) {
            top.linkTo(bt4.bottom)
            start.linkTo(parent.start)
        }
        constrain(bt8) {
            top.linkTo(bt5.bottom)
            start.linkTo(bt7.end)
        }
        constrain(bt9) {
            top.linkTo(bt6.bottom)
            start.linkTo(bt8.end)
        }
        createHorizontalChain(bt1, bt2, bt3, chainStyle = ChainStyle.Packed(0.5F))
        createHorizontalChain(bt4, bt5, bt6, chainStyle = ChainStyle.Packed(0.5F))
        createHorizontalChain(bt7, bt8, bt9, chainStyle = ChainStyle.Packed(0.5F))

        createVerticalChain(bt1, bt4, bt7, chainStyle = ChainStyle.Packed(0.5f))
        createVerticalChain(bt2, bt5, bt8, chainStyle = ChainStyle.Packed(0.5f))
        createVerticalChain(bt3, bt6, bt9, chainStyle = ChainStyle.Packed(0.5f))
    }
    ConstraintLayout(constraints, modifier = modifier
        .fillMaxSize()
        .background(Color.Gray)) {
        GameButton(text = board[0], Modifier.layoutId("bt1")) { onClick(0) }
        GameButton(text = board[1], Modifier.layoutId("bt2")) { onClick(1) }
        GameButton(text = board[2], Modifier.layoutId("bt3")) { onClick(2) }

        GameButton(text = board[3], Modifier.layoutId("bt4")) { onClick(3) }
        GameButton(text = board[4], Modifier.layoutId("bt5")) { onClick(4) }
        GameButton(text = board[5], Modifier.layoutId("bt6")) { onClick(5) }

        GameButton(text = board[6], Modifier.layoutId("bt7")) { onClick(6) }
        GameButton(text = board[7], Modifier.layoutId("bt8")) { onClick(7) }
        GameButton(text = board[8], Modifier.layoutId("bt9")) { onClick(8) }
    }
}

@Composable
fun GameButton(text: String, modifier: Modifier, onclick: () -> Unit) {
    Box(modifier = modifier
        .padding(2.dp)
        .border(1.dp, BlueStroke, RoundedCornerShape(3.dp))
        .background(
            Brush.verticalGradient(colors = listOf(Blue2, Blue2))
        )
        .width(100.dp)
        .height(100.dp)
    ) {
        TextButton(
            onClick = onclick,
            enabled = text.isBlank(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = text,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp,
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}