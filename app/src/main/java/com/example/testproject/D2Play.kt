package com.example.testproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import kotlin.math.abs
import kotlin.system.exitProcess


@Suppress("DEPRECATION")
class D2Play : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            val gf = GameField() // generate first cells with 2
            val stateOfVisible by remember { mutableStateOf(MutableList(16) { false }) }
            var pair = generateNewCell(gf.getEmpty())
            stateOfVisible[pair.second * 3 + pair.first] = true
            gf.setState(
                pair.first,
                pair.second,
                2
            )
            pair = generateNewCell(gf.getEmpty())
            stateOfVisible[pair.second * 3 + pair.first] = true
            gf.setState(
                pair.first,
                pair.second,
                2
            )

            var displayField by remember { mutableStateOf(gf.getList()) }
            var score by remember { mutableStateOf(0) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(240, 197, 152, 255))
                    .offset(0.dp, (-40).dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.BottomCenter)
                        .height(384.dp)
                        .width(390.dp)
                        .padding(start = 3.dp, end = 3.dp, bottom = 450.dp, top = 200.dp)
                        .background(Color(61, 59, 59, 255), shape = RoundedCornerShape(15.dp)),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextField(
                        value = score.toString(),
                        label = { Text("Score") },
                        onValueChange = { },
                        maxLines = 1,
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 12.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .width(150.dp)
                            .fillMaxHeight()
                            .clip(shape = RoundedCornerShape(15.dp)),
                        textStyle = TextStyle(
                            fontSize = 21.sp,
                            baselineShift = BaselineShift(-0.7f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(500),
                            color = Color(63, 81, 181, 255)
                        )
                    )
                    val reader = File(applicationContext.filesDir, "temp.txt").reader()
                    TextField(
                        value = reader.readText().also { reader.close() },
                        label = { Text("TopScore") },
                        onValueChange = { },
                        maxLines = 1,
                        readOnly = true,
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 12.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .width(150.dp)
                            .fillMaxHeight()
                            .clip(shape = RoundedCornerShape(15.dp)),
                        textStyle = TextStyle(
                            fontSize = 21.sp,
                            baselineShift = BaselineShift(-0.7f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(500),
                            color = Color(63, 81, 181, 255)
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .height(384.dp)
                        .width(390.dp)
                        .padding(start = 3.dp, end = 3.dp)
                        .background(Color(61, 59, 59, 255), shape = RoundedCornerShape(15.dp)),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (j in 0..3) {
                        Column(
                            modifier = Modifier
                                .width(80.dp)
                                .height(390.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (i in 0..3) {
                                TextField(
                                    value = "",
                                    onValueChange = { },
                                    maxLines = 1,
                                    readOnly = true,
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFFB9B3B3),
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .width(80.dp)
                                        .height(80.dp)
                                        .clip(shape = RoundedCornerShape(15.dp)),
                                    textStyle = TextStyle(
                                        fontSize = 21.sp,
                                        baselineShift = BaselineShift(-0.4f),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight(500),
                                        color = Color(255, 255, 255, 255)
                                    )
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .height(384.dp)
                        .width(390.dp)
                        .padding(start = 3.dp, end = 3.dp)
                        .background(Color(0, 0, 0, 0), shape = RoundedCornerShape(15.dp)),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (j in 0..3) {
                        Column(
                            modifier = Modifier
                                .width(80.dp)
                                .height(390.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (i in 0..3) {
                                AnimatedVisibility(
                                    visible = true,
                                    enter = scaleIn(),
                                    exit = scaleOut()
                                ) {
                                    TextField(
                                        value = if (displayField[j][i].toString() == "0") "" else displayField[j][i].toString(),
                                        onValueChange = { },
                                        maxLines = 1,
                                        readOnly = true,
                                        modifier = Modifier
                                            .background(
                                                color = getColors(displayField[j][i]),
                                                shape = RoundedCornerShape(15.dp)
                                            )
                                            .width(80.dp)
                                            .height(80.dp)
                                            .clip(shape = RoundedCornerShape(15.dp)),
                                        textStyle = TextStyle(
                                            fontSize = 21.sp,
                                            baselineShift = BaselineShift(-0.4f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight(500),
                                            color = Color(255, 255, 255, 255)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            var direction by remember { mutableStateOf(-1) }
            val ctrl by remember { mutableStateOf(mutableListOf<Int>()) }

            if (score == 3) {
                AlertDialog(
                    onDismissRequest = {
                        false
                    },
                    buttons = {
                        Button(
                            onClick = {
                                intent.putExtra("key", "2D")
                                setResult(RESULT_OK, intent)
                                finish()
                            },
                            modifier = Modifier
                                .width(290.dp)
                                .height(90.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xA9FF77CD)),
                            border = BorderStroke(
                                2.7.dp,
                                Color(red = 0.8f, green = 0.5f, blue = 1f)
                            )
                        ) {
                            Text(
                                "Lose, REVENGE? ❤️", fontSize = 24.sp,
                                color = Color(red = 1f, green = 1f, blue = 1f),
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily.Serif
                            )

                        }
                        Button(
                            onClick = {
                                intent.putExtra("key", "Menu")
                                setResult(RESULT_OK, intent)
                                finish()
                            },
                            modifier = Modifier
                                .width(290.dp)
                                .height(90.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xA9FF77CD)),
                            border = BorderStroke(
                                2.7.dp,
                                Color(red = 0.8f, green = 0.5f, blue = 1f)
                            )
                        ) {
                            Text(
                                "Menu ❤️", fontSize = 24.sp,
                                color = Color(red = 1f, green = 1f, blue = 1f),
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }
                )
            } else if (score == 5) {
                AlertDialog(
                    onDismissRequest = {
                        false
                    },
                    buttons = {
                        Button(
                            onClick = {
                                intent.putExtra("key", "Menu")
                                setResult(RESULT_OK, intent)
                                finish()
                            },
                            modifier = Modifier
                                .width(290.dp)
                                .height(90.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9800)),
                            border = BorderStroke(
                                2.7.dp,
                                Color(red = 255, green = 235, blue = 59, alpha = 255)
                            )
                        ) {
                            Text(
                                "Victory ❤️", fontSize = 24.sp,
                                color = Color(red = 255, green = 87, blue = 34, alpha = 255),
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily.Serif
                            )

                        }
                    }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()

                                val (x, y) = dragAmount
                                if (abs(x) > 30 || abs(y) > 30) {
                                    if (abs(x) > abs(y)) {
                                        when {
                                            x > 0 -> {
                                                //right
                                                direction = 0
                                            }
                                            x < 0 -> {
                                                // left
                                                direction = 1
                                            }
                                        }
                                    } else {
                                        when {
                                            y > 0 -> {
                                                // down
                                                direction = 2
                                            }
                                            y < 0 -> {
                                                // up
                                                direction = 3
                                            }
                                        }
                                    }
                                }

                            },
                            onDragEnd = {
                                score = move(direction, gf, score, ctrl, stateOfVisible)
                                val file = File(applicationContext.filesDir, "temp.txt")
                                file.createNewFile()
                                val reader = file.reader()
                                val curBestScore = reader.readText()

                                reader.close()
                                if (curBestScore == "" || (curBestScore.toInt() <= score && score != 3)) {
                                    val writer = file.writer()
                                    writer.write(
                                        score.toString()
                                    )
                                    writer.close()
                                }

//                                for (j in 0..3) {
//                                    for (i in gf.theField) {
//                                        print("${i[j]} ")
//                                    }
//                                    println()
//                                }
                                displayField = gf.getList()
                            }
                        )
                    }
            )
        }
    }
}


@Preview
@Composable
fun Preview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(240, 197, 152, 255))
            .offset(0.dp, (-40).dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .height(384.dp)
                .width(390.dp)
                .padding(start = 3.dp, end = 3.dp, bottom = 450.dp, top = 300.dp)
                .background(Color(61, 59, 59, 255), shape = RoundedCornerShape(15.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextField(
                value = "0",
                label = { Text("Score") },
                onValueChange = { },
                maxLines = 1,
                readOnly = true,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .width(150.dp)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(15.dp)),
                textStyle = TextStyle(
                    fontSize = 21.sp,
                    baselineShift = BaselineShift(-0.4f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500),
                    color = Color(255, 255, 255, 255)
                )
            )
            TextField(
                value = "0",
                label = { Text("TopScore") },
                onValueChange = { },
                maxLines = 1,
                readOnly = true,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .width(150.dp)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(15.dp)),
                textStyle = TextStyle(
                    fontSize = 21.sp,
                    baselineShift = BaselineShift(-0.4f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500),
                    color = Color(255, 255, 255, 255)
                )
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(384.dp)
                .width(390.dp)
                .padding(start = 3.dp, end = 3.dp)
                .background(Color(61, 59, 59, 255), shape = RoundedCornerShape(15.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (j in 0..3) {
                Column(
                    modifier = Modifier
                        .width(80.dp)
                        .height(390.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (i in 0..3) {
                        TextField(
                            value = "",
                            onValueChange = { },
                            maxLines = 1,
                            readOnly = true,
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .width(80.dp)
                                .height(80.dp)
                                .clip(shape = RoundedCornerShape(15.dp)),
                            textStyle = TextStyle(
                                fontSize = 21.sp,
                                baselineShift = BaselineShift(-0.4f),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight(500),
                                color = Color(255, 255, 255, 255)
                            )
                        )
                    }
                }
            }
        }
    }
}

// Game Field XD - public class with state of every cells
class GameField {

    var theField = MutableList(4) { MutableList(4) { 0 } } // matrix


    // state of cell
    fun getState(x: Int, y: Int): Int {
        return theField[x][y]
    }

    // set new state
    fun setState(x: Int, y: Int, state: Int) {
        theField[x][y] = state
    }


    //empty cells
    fun getEmpty(): List<Int> {
        var emptyCells = mutableListOf<Int>()
        for (i in 0..15) {
            if (theField[i % 4][i / 4] == 0) {
                emptyCells.add(i)
            }
        }
        return emptyCells
    }


    fun setColumn(x: Int, newColumn: MutableList<Int>) {
        theField[x] = newColumn
    }


    fun getColumn(i: Int): MutableList<Int> {
        return theField[i]
    }


    fun setLine(i: Int, newLine: MutableList<Int>) {
        for (j in 0 until 4) {
            theField[j][i] = newLine[j]
        }
    }


    fun getLine(i: Int): MutableList<Int> {
        val ret = mutableListOf<Int>()
        for (j in 0 until 4) {
            ret.add(theField[j][i])
        }
        return ret
    }

    fun getList(): List<List<Int>> {
        val list = MutableList(4) { List(4) { 0 } }
        for (i in 0..3) {
            list[i] = this.theField[i].toList()
        }
        return list.toList()
    }

}


fun move(
    direction: Int,
    gf: GameField,
    score: Int,
    ctrl: MutableList<Int>,
    stateOfVisible: MutableList<Boolean>
): Int {
    var gf = gf
    val moves = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
    var save = gf
    var score = score
    if (direction == 2 || direction == 3) {
        for (i in 0..3) {
            val getColumn = gf.getColumn(i)
            if (direction == 2) {
                for (j in 2 downTo 0) {
                    if (getColumn[j] != 0) {
                        for (k in (j + 1)..3) {
                            if (getColumn[k] == 0) {
                                if (k == 3) {
                                    getColumn[k] = getColumn[j]
                                    moves.add(Pair(Pair(j, i), Pair(k, i)))
                                    stateOfVisible[i * 3 + j] = false
                                    stateOfVisible[i * 3 + k] = true
                                    getColumn[j] = 0
                                    break
                                } else continue
                            } else if (getColumn[k] == getColumn[j]) {
                                moves.add(Pair(Pair(j, i), Pair(k, i)))
                                stateOfVisible[i * 3 + j] = false
                                stateOfVisible[i * 3 + k] = true
                                getColumn[j] = 0
                                score += getColumn[k] * 2
                                getColumn[k] *= 2
                                break
                            } else {
                                getColumn[k - 1] = getColumn[j]
                                if (j != k - 1) {
                                    moves.add(Pair(Pair(j, i), Pair(k - 1, i)))
                                    stateOfVisible[i * 3 + j] = false
                                    stateOfVisible[i * 3 + k - 1] = true
                                    getColumn[j] = 0
                                }
                                break
                            }

                        }
                    }
                }
            } else if (direction == 3) {
                for (j in 1..3) {
                    if (getColumn[j] != 0) {
                        for (k in (j - 1) downTo 0) {
                            if (getColumn[k] == 0) {
                                if (k == 0) {
                                    getColumn[k] = getColumn[j]
                                    moves.add(Pair(Pair(j, i), Pair(k, i)))
                                    stateOfVisible[i * 3 + j] = false
                                    stateOfVisible[i * 3 + k] = true
                                    getColumn[j] = 0
                                    break
                                } else continue
                            } else if (getColumn[k] == getColumn[j]) {
                                moves.add(Pair(Pair(j, i), Pair(k, i)))
                                stateOfVisible[i * 3 + j] = false
                                stateOfVisible[i * 3 + k] = true
                                getColumn[j] = 0
                                score += getColumn[k] * 2
                                getColumn[k] *= 2
                                break
                            } else {
                                getColumn[k + 1] = getColumn[j]
                                if (j != k + 1) {
                                    stateOfVisible[i * 3 + j] = false
                                    moves.add(Pair(Pair(j, i), Pair(k + 1, i)))
                                    stateOfVisible[i * 3 + k + 1] = true
                                    getColumn[j] = 0
                                }
                                break
                            }
                        }
                    }
                }
            }
            gf.setColumn(i, getColumn)
        }
    } else {
        for (i in 0..3) {
            val getLine = gf.getLine(i)
            if (direction == 0) {
                for (j in 2 downTo 0) {
                    if (getLine[j] != 0) {
                        for (k in (j + 1)..3) {
                            if (getLine[k] == 0) {
                                if (k == 3) {
                                    getLine[k] = getLine[j]
                                    stateOfVisible[j * 3 + i] = false
                                    moves.add(Pair(Pair(i, j), Pair(i, k)))
                                    stateOfVisible[k * 3 + i] = true
                                    getLine[j] = 0
                                    break
                                } else continue
                            } else if (getLine[k] == getLine[j]) {
                                stateOfVisible[j * 3 + i] = false
                                moves.add(Pair(Pair(i, j), Pair(i, k)))
                                stateOfVisible[k * 3 + i] = true
                                getLine[j] = 0
                                score += getLine[k] * 2
                                getLine[k] *= 2
                                break
                            } else {
                                getLine[k - 1] = getLine[j]
                                if (j != k - 1) {
                                    stateOfVisible[j * 3 + i] = false
                                    moves.add(Pair(Pair(i, j), Pair(i, k - 1)))
                                    stateOfVisible[(k - 1) * 3 + i] = true
                                    getLine[j] = 0
                                }
                                break
                            }
                        }
                    }
                }
            } else if (direction == 1) {
                for (j in 1..3) {
                    if (getLine[j] != 0) {
                        for (k in (j - 1) downTo 0) {
                            if (getLine[k] == 0) {
                                if (k == 0) {
                                    moves.add(Pair(Pair(i, j), Pair(i, k)))
                                    stateOfVisible[j * 3 + i] = false
                                    stateOfVisible[k * 3 + i] = true
                                    getLine[k] = getLine[j]
                                    getLine[j] = 0
                                    break
                                } else continue
                            } else if (getLine[k] == getLine[j]) {
                                moves.add(Pair(Pair(i, j), Pair(i, k)))
                                stateOfVisible[j * 3 + i] = false
                                stateOfVisible[k * 3 + i] = true
                                getLine[j] = 0
                                score += getLine[k] * 2
                                getLine[k] *= 2
                                break
                            } else {
                                getLine[k + 1] = getLine[j]
                                if (j != k + 1) {
                                    moves.add(Pair(Pair(i, j), Pair(i, k + 1)))
                                    stateOfVisible[j * 3 + i] = false
                                    stateOfVisible[(k + 1) * 3 + i] = true
                                    getLine[j] = 0
                                }
                                break
                            }
                        }
                    }
                }
            }
            gf.setLine(i, getLine)
        }
    }
    for (i in 0..3) {
        for (j in 0..3) {
            if (gf.theField[i][j] == 2048) return 5
        }
    }
    if (gf.getEmpty().isEmpty()) {
        ctrl.add(direction)
        //println(ctrl)
        return if (3 in ctrl && 2 in ctrl && 1 in ctrl && 0 in ctrl) {
            3
        } else {
            score
        }
    }
    ctrl.clear()
    if (moves.isNotEmpty()) {
        //println(moves)
        val pair = generateNewCell(gf.getEmpty())
        gf.setState(
            pair.first,
            pair.second,
            2
        )
    } else {
        gf = save
    }
    return score
}

private fun generateNewCell(emptyCells: List<Int>): Pair<Int, Int> {
    val random = emptyCells.random()
    return Pair(random % 4, random / 4)
}

fun getColors(number: Int): Color {
    return when (number) {
        2 -> Color(0xff50c0e9)
        4 -> Color(0xff1da9da)
        8 -> Color(0xffcb97e5)
        16 -> Color(0xffb368d9)
        32 -> Color(0xffff5f5f)
        64 -> Color(0xffe92727)
        128 -> Color(0xff92c500)
        256 -> Color(0xff7caf00)
        512 -> Color(0xffffc641)
        1024 -> Color(0xffffa713)
        2048 -> Color(0xffff8a00)
        else -> Color(0xFFB9B3B3)
    }
}

