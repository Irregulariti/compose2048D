package com.example.testproject

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testproject.ui.theme.TestprojectTheme

@Suppress("DEPRECATION")
class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            val colorStops = arrayOf(
                0.0f to Color(0xFF735777),
                0.5f to Color(0xFF346794),
                1f to Color(0xFF735777)
            )
            TestprojectTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.horizontalGradient(colorStops = colorStops))
                ) {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.TopCenter)
                            .padding(top = 2.dp),
                        painter = painterResource(id = R.drawable.front_name),
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(400.dp)
                            .padding(top = 50.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,

                    ) {
                        Button(
                            onClick = {
                                intent.putExtra("key","2D")
                                setResult(RESULT_OK,intent)
                                finish()
                            },
                            modifier = Modifier
                                .width(290.dp)
                                .height(90.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xA9FF77CD)),
                            border = BorderStroke(2.7.dp, Color(red = 0.8f, green = 0.5f, blue = 1f))
                        ) {
                            Text("Play 2D ❤️", fontSize = 27.sp,
                                color = Color(red = 1f, green = 1f, blue = 1f),
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Button(
                            onClick = {
                                      intent.putExtra("key","3D")
                                setResult(RESULT_OK,intent)
                                finish()
                            },
                            modifier = Modifier
                                .width(290.dp)
                                .height(90.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xA9FF77CD)),
                            border = BorderStroke(2.7.dp, Color(red = 0.8f, green = 0.5f, blue = 1f))
                        ) {
                            Text("Play 3D ❤️", fontSize = 27.sp,
                                color = Color(red = 1f, green = 1f, blue = 1f),
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily.Serif
                                )
                        }
                        Button(
                            onClick = {
                                intent.putExtra("key","Options")
                                setResult(RESULT_OK,intent)
                                finish()
                            },
                            modifier = Modifier
                                .width(290.dp)
                                .height(90.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xA9FF77CD)),
                            border = BorderStroke(2.7.dp, Color(red = 0.8f, green = 0.5f, blue = 1f))
                        ) {
                            Text("Options ❤️", fontSize = 27.sp,
                                color = Color(red = 1f, green = 1f, blue = 1f),
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }
                }
            }
        }
    }
}

