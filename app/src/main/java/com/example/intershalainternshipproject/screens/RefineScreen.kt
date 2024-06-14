package com.example.intershalainternshipproject.screens


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intershalainternshipproject.R

class RefineScreen : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Refine", color = Color.White)
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(
                            1,
                            34,
                            90,
                            255
                        ),
                    )
                )
            }) {

                Box(modifier = Modifier.padding(it).padding(20.dp)) {
                    RefineScreenView()
                }
            }
        }
    }

    @Composable
    fun RefineScreenView(modifier: Modifier = Modifier) {
        val dropDownBoxContent = listOf(
            "Available | Hey Let Us Connect ",
            "Away | Stay Discrete And Watch",
            "Busy | Do Not Disturb | Will Catch Up Later",
            "SOS | Emergency! Need Assistance! HELP"
        )
        val downArrowImage = painterResource(id = R.drawable.baseline_arrow_drop_down_24)
        var status by remember {
            mutableStateOf("Hi community! I am open to new connections 😊")
        }
        var sliderPosition by remember { mutableFloatStateOf(1f) }
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        var wordsCount by remember {
            mutableIntStateOf(47)
        }
        var purposeMap by remember {
            mutableStateOf(
                mutableMapOf(
                    "Coffee" to true,
                    "Business" to true,
                    "Hobbies" to true,
                    "Friendship" to true,
                    "Movies" to false,
                    "Dinning" to false,
                    "Dating" to false,
                    "Matrimony" to false
                )
            )
        }
        var backColor: Color
        var frontColor: Color

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column {
                Text(
                    text = "Select Your Availability",
                    color = Color(35, 75, 143, 255),
                    fontWeight = FontWeight.Bold
                )
                DropDownMenuBox(dropDownBoxContent, downArrowImage)
            }
            Column(
                modifier = modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                Box(modifier = modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = "Add Your Status", color = Color(35, 75, 143, 255),
                        fontWeight = FontWeight.Bold
                    )
                }
                OutlinedTextField(
                    value = status,
                    onValueChange = { value ->
                        if (value.isEmpty()) wordsCount = 0
                        if (value.isNotEmpty() && value.length > status.length) ++wordsCount
                        if (value.isNotEmpty() && value.length < status.length) --wordsCount
                        status = value
                    },
                    modifier = modifier
                        .clip(RoundedCornerShape(20))
                        .fillMaxWidth(),
                    textStyle = TextStyle(Color.Black),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Gray,
                        focusedIndicatorColor = Color.Gray
                    ),

                    )
                Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text(text = "$wordsCount/250", fontSize = 12.sp, color = Color.DarkGray)
                }
            }
            Column(
                modifier = modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                Box(modifier = modifier.padding(bottom = 10.dp)) {
                    Text(
                        text = "Select Hyper local Distance", color = Color(35, 75, 143, 255),
                        fontWeight = FontWeight.Bold
                    )
                    Column(modifier = modifier.padding(top = 35.dp)) {
                        Box(
                            modifier = modifier
                                .offset(x = (sliderPosition * (screenWidth.value - 100) / 100).dp)
                                .requiredSize(40.dp)
                                .background(Color(35, 75, 143, 255), shape = pointedBottomShape()),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = sliderPosition.toInt().toString(),
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 7.dp)
                            )
                        }
                        Slider(
                            value = sliderPosition,
                            onValueChange = { range ->
                                sliderPosition = range
                            },
                            valueRange = 1f..100f,
                            modifier = modifier.fillMaxWidth()
                        )
                        Row(modifier = modifier.fillMaxWidth()) {

                            Box(modifier = modifier.fillMaxWidth(0.5f)) {
                                Text(text = "1Km", fontSize = 15.sp, color = Color.DarkGray)
                            }
                            Box(
                                modifier = modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(text = "100Km", fontSize = 15.sp, color = Color.DarkGray)
                            }
                        }
                    }


                }
            }
            Column(modifier = modifier.padding(top = 10.dp)) {
                Box(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = "Select Purpose", color = Color(35, 75, 143, 255),
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                ) {
                    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp)) {
                        items(purposeMap.entries.toList()) { (purpose, selected) ->
                            backColor = Color.White
                            frontColor = Color.DarkGray
                            if (selected) {
                                backColor = Color(35, 75, 143, 255)
                                frontColor = Color.White
                            }
                            Box(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .padding(horizontal = 5.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Blue,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clickable(onClick = {
                                        if (purposeMap[purpose] == true) {
                                            purposeMap = purposeMap
                                                .toMutableMap()
                                                .apply {
                                                    this[purpose] = false
                                                }
                                        } else {
                                            purposeMap = purposeMap
                                                .toMutableMap()
                                                .apply {
                                                    this[purpose] = true
                                                }
                                        }
                                        Log.d("pop", "RefineScreen: $purposeMap")
                                    })
                                    .background(backColor, shape = RoundedCornerShape(10)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = purpose,
                                    modifier = Modifier.padding(vertical = 4.dp), color = frontColor
                                )
                            }
                        }
                    }
                }

            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), contentAlignment = Alignment.Center
            )
            {
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(35, 75, 143, 255), contentColor = Color.White

                    )
                ) {
                    Text(text = "Save and Explore", color = Color.White)
                }
            }
        }

    }

    private fun pointedBottomShape(): GenericShape {
        return GenericShape { size, _ ->
            val width = size.width
            val height = size.height
            val triangleHeight = 20f
            val triangleWidth = 40f

            moveTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, height - triangleHeight)
            lineTo(width / 2 + triangleWidth / 2, height - triangleHeight)
            lineTo(width / 2, height)
            lineTo(width / 2 - triangleWidth / 2, height - triangleHeight)
            lineTo(0f, height - triangleHeight)
            close()
        }
    }

    @Composable
    fun DropDownMenuBox(
        dropDownBoxContent: List<String>,
        downArrowImage: Painter,
        modifier: Modifier = Modifier
    ) {
        var mExpanded by remember {
            mutableStateOf(false)
        }
        var selectedText by remember { mutableStateOf(dropDownBoxContent[0]) }
        Column(
            modifier = modifier
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(10))
        ) {
            OutlinedTextField(
                value = selectedText,
                textStyle = TextStyle(color = Color.Black),
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(downArrowImage, "contentDescription",
                        modifier.clickable { mExpanded = !mExpanded })
                }
            )
            DropdownMenu(expanded = mExpanded, onDismissRequest = { mExpanded = false }) {
                dropDownBoxContent.forEach { item ->
                    DropdownMenuItem(text = { Text(text = item) }, onClick = {
                        selectedText = item
                        mExpanded = false
                    }, modifier = modifier.padding(horizontal = 20.dp))
                }

            }
        }

    }

}