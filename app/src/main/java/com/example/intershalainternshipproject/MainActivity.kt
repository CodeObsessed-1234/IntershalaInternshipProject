package com.example.intershalainternshipproject


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.intershalainternshipproject.screens.ExploreScreenView
import com.example.intershalainternshipproject.screens.RefineScreen
import com.example.intershalainternshipproject.ui.theme.IntershalaInternshipProjectTheme


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            IntershalaInternshipProjectTheme {

                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = {
                        Text(text = "Howdy Nitin Dwivedi !!", color = Color.White)
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(
                            1,
                            34,
                            90,
                            255
                        ),
                    ), actions = {
                        IconButton(onClick = {
                            val intent = Intent(context, RefineScreen::class.java)
                            context.startActivity(intent)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    })
                }) {

                    Box(modifier = Modifier.padding(it)) {
                        ExploreScreenView()
                    }
                }
            }
        }
    }
}
