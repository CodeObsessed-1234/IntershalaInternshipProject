package com.example.intershalainternshipproject.screens.explorescreencomponents


import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.intershalainternshipproject.R

data class ServiceDataContainer(
    var shortForm: String,
    var name: String,
    var location: String,
    var invited: Boolean,
    var positionKM: Float,
    var profession: String,
    var profileScore: Int,
    var yearsOfExperience: Int,
    var description: String,
)

@Preview
@Composable
fun ServiceView(modifier: Modifier = Modifier) {
    var searchText by remember {
        mutableStateOf("")
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(10)),
                value = searchText,
                onValueChange = {
                    searchText = it

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                    )
                },
                shape = MaterialTheme.shapes.medium.copy(
                    all = CornerSize(26.dp)
                ),
                textStyle = TextStyle(color = Color.Black),

                placeholder = { Text("Search", color = Color(0, 48, 99, 255)) },

                )
            Box(
                modifier = modifier
                    .requiredWidth(100.dp)
                    .padding(top = 10.dp, start = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_menu_open_24),
                    contentDescription = "menu"
                )
            }
        }
        ServiceCards(searchText)
    }
}


@Composable
fun ServiceCards(searchedText: String) {
    val personData = listOf(
        ServiceDataContainer(
            shortForm = "",
            name = "Nitin Pandey",
            "Lucknow",
            false,
            5.2f,
            "Intern",
            30,
            0,
            "Hi i am Nitin Pandey I'm recent alumni of NIFT, Banglore Works as Ui | UX Desiner I provide genuine solution to products and company..."
        ),
        ServiceDataContainer(
            shortForm = "PP",
            name = "Pratek Pandey",
            "Lucknow",
            false,
            5.2f,
            "Intern",
            30,
            1,
            "I am Architecture",
        ),
        ServiceDataContainer(
            shortForm = "AS",
            name = "Aditya Sriwastav",
            "Lucknow",
            false,
            5.2f,
            "Android Developer",
            70,
            2,
            "",
        ),
    )
    Log.d("TAG", "PersonalCards: $searchedText")
    val text = searchedText.lowercase()
    val searchedData =
        if (searchedText.isNotEmpty()) personData.filter {
            (it.name.lowercase().contains(text) ||
                    it.location.lowercase().contains(text) ||
                    it.profession.lowercase().contains(text) ||
                    it.shortForm.lowercase().contains(text))
        } else personData



    Column {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                if (searchedData.isNotEmpty()) {
                    searchedData.forEach { index ->
                        Box(
                            modifier = Modifier
                                .zIndex(5.0f)
                                .padding(start = 5.dp)
                        ) {
                            if (index.shortForm.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(75.dp)
                                        .background(
                                            Color(204, 204, 204, 255),
                                            RoundedCornerShape(12)
                                        )

                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.user),
                                        contentDescription = "userImage",
                                        contentScale = ContentScale.Fit
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .requiredWidth(75.dp)
                                        .background(
                                            Color(204, 204, 204, 255),
                                            RoundedCornerShape(12)
                                        )
                                        .padding(20.dp)

                                ) {
                                    Text(
                                        text = index.shortForm,
                                        color = Color(0, 48, 99, 255),
                                        style = TextStyle(fontSize = 25.sp),
                                        fontWeight = FontWeight.Bold
                                    )

                                }
                            }
                        }

                        ElevatedCard(
                            modifier = Modifier
                                .offset(y = (-70).dp)
                                .zIndex(1.0f)
                                .padding(start = 40.dp, end = 10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(
                                    252, 252, 252,
                                    255
                                )
                            ),
                            shape = MaterialTheme.shapes.medium
                        ) {

                            Column {
                                Row(modifier = Modifier.padding(start = 40.dp)) {
                                    Row(
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                            .fillMaxWidth()
                                    ) {

                                        Column {
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                text = index.name,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                style = TextStyle(fontSize = 20.sp)
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .padding(top = 5.dp),
                                                text = "${index.location}, Within ${index.positionKM} KM",
                                                color = Color.Black,
                                                style = TextStyle(fontSize = 11.sp)
                                            )

                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 10.dp, start = 20.dp, end = 10.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .requiredWidth(20.dp)
                                                    .padding(top = 5.dp)
                                            ) {
                                                Image(
                                                    contentScale = ContentScale.Fit,
                                                    painter = painterResource(id = R.drawable.baseline_add_24),
                                                    contentDescription = "invite"
                                                )
                                            }
                                            Box {
                                                Text(
                                                    text = "INVITE",
                                                    style = TextStyle(fontSize = 20.sp),
                                                    color = Color(
                                                        43,
                                                        11,
                                                        187,
                                                        255
                                                    ), fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }

                                    }
                                }
                                Row(modifier = Modifier.padding(start = 50.dp)) {
                                    Canvas(
                                        modifier = Modifier
                                            .requiredWidth(100.dp)
                                            .padding(vertical = 10.dp)
                                    ) {
                                        val width = 250.dp
                                        val height = 20.dp / 2

                                        // Draw the inactive track
                                        drawLine(
                                            color = Color.Gray,
                                            start = Offset(0f, height.value),
                                            end = Offset(width.value, height.value),
                                            strokeWidth = 8.dp.toPx()
                                        )

                                        // Draw the active track
                                        drawLine(
                                            color = Color.DarkGray,
                                            start = Offset(0f, height.value),
                                            end = Offset(
                                                index.profileScore.toFloat() * 2.5f,
                                                height.value
                                            ),
                                            strokeWidth = 8.dp.toPx()
                                        )
                                    }
                                    Box(modifier = Modifier.padding(top = 7.dp, start = 40.dp)) {

                                        Text(
                                            text = "Profile Score - ${index.profileScore}%",
                                            style = TextStyle(fontSize = 11.sp),
                                            color = Color.Gray
                                        )
                                    }
                                }
                                Row(modifier = Modifier.padding(start = 50.dp, top = 10.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                Color(1, 1, 87, 255),
                                                shape = RoundedCornerShape(50.dp)
                                            )
                                            .padding(10.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.baseline_call_24),
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier.requiredWidth(12.dp),
                                            contentDescription = "call"
                                        )
                                    }
                                    Box(modifier = Modifier.padding(start = 15.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.baseline_perm_contact_calendar_24),
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.requiredWidth(30.dp),
                                            contentDescription = "contact"
                                        )
                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 5.dp, bottom = 5.dp)
                                ) {

                                    Row {
                                        Text(
                                            text = "${index.profession} | ${index.yearsOfExperience} years of experience",
                                            color = Color(0, 55, 92, 255),
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                top = 5.dp,
                                                bottom = 5.dp
                                            ), fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 20.dp, top = 5.dp, bottom = 5.dp)
                                            .requiredWidth(250.dp)
                                    ) {
                                        Text(
                                            text = "Hi community! I am available at your service!",
                                            color = Color(0, 55, 92, 203),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Text(
                                        text = index.description, modifier = Modifier.padding(
                                            start = 20.dp,
                                        ), color = Color.Black, fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No Record Found",
                            color = Color.Black,
                            style = TextStyle(fontSize = 35.sp)
                        )
                    }
                }
            }
        }
    }
}


