package com.example.testproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testproject.ui.theme.TestprojectTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            when (data.getStringExtra("key")) {
                "2D" -> {
                    val d2intent = Intent(this, D2Play::class.java)
                    startActivityForResult(d2intent, 100)
                }
                "3D" -> {
                    val menuIntent = Intent(this, MenuActivity::class.java)
                    startActivityForResult(menuIntent, 100)
                }
                "Options" -> {
                    val menuIntent = Intent(this, MenuActivity::class.java)
                    startActivityForResult(menuIntent, 100)
                }
                "Menu" -> {
                    val menuIntent = Intent(this, MenuActivity::class.java)
                    startActivityForResult(menuIntent, 100)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val menuIntent = Intent(this, MenuActivity::class.java)
            startActivityForResult(menuIntent, 100)
            //          TopAppBar {
//                IconButton(onClick = { }) {
//                    Icon(Icons.Filled.Menu, contentDescription = "Меню")
//                }
//                Text("2048 omfnpos", fontSize = 22.sp)
//                Spacer(Modifier.weight(1f, true))
//                IconButton(onClick = { }) {
//                    Icon(Icons.Filled.Info, contentDescription = "Информация о приложении")
//                }
//                IconButton(onClick = { }) {
//                    Icon(Icons.Filled.Search, contentDescription = "Поиск")
//                }
//            }
//            TestprojectTheme {
//                Box(
//                ) {
//                    Image(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(bottom = 10.dp),
//                        alignment = Alignment.BottomCenter,
//                        painter = painterResource(id = R.drawable.field),
//                        contentDescription = null
//                    )
//                }
//            }
        }
    }
}


//@Composable
//fun PrimitivePreview(visible: Boolean){
//    AnimatedVisibility(
//        visible = visible,
//        enter = slideInHorizontally() + expandHorizontally(expandFrom = Alignment.End)
//                + fadeIn(),
//        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })
//                + shrinkHorizontally() + fadeOut(),
//    ) {
//        Image(
//            modifier = Modifier.fillMaxWidth(),
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "",
//        )
//    }
//    AnimatedVisibility(
//        visible = visible,
//        enter = fadeIn(animationSpec = tween(durationMillis = 300, easing = LinearEasing)),
//        exit = fadeOut(animationSpec = tween(durationMillis = 300)),
//    ) {
//        Image(
//            modifier = Modifier.fillMaxWidth(),
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "",
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}

