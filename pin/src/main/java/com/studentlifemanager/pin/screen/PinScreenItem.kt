package com.studentlifemanager.pin.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studentlifemanager.pin.R


@Composable
fun PinItem() {
    Column(
        modifier = Modifier.border(
            BorderStroke(1.dp, color = Color.LightGray)
        )
    ) {
        Row(
            modifier = Modifier
                .height(40.dp)
                .background(Color.LightGray)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "T",
                modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color.White
                ),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Google",
                modifier = Modifier.padding(end = 15.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White
                ),
                fontWeight = FontWeight.Normal
            )
        }
        Row(
            modifier = Modifier
                .height(30.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.List,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                tint = Color.Gray
            )
            Text(
                text = "Math",
                modifier = Modifier.padding(end = 15.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray
                ),
                fontWeight = FontWeight.Normal
            )
        }

        Row(
            modifier = Modifier
                .height(30.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Send,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                tint = Color.Gray

            )
            Text(
                text = "https://www.dictionary.com/browse/example",
                modifier = Modifier.padding(end = 15.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray
                ),
                fontWeight = FontWeight.Normal
            )
        }

        Row(
            modifier = Modifier
                .height(30.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.DateRange,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                tint = Color.Gray

            )
            Text(
                text = "12 may 2023",
                modifier = Modifier.padding(end = 15.dp),
                style = TextStyle(
                    fontSize = 13.sp,
                    color = Color.Gray
                ),
                fontWeight = FontWeight.Normal
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxSize()
        ) {
            Icon(
                Icons.Filled.Share,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.padding(end = 30.dp)
            )
            Icon(
                Icons.Filled.ExitToApp,
                contentDescription = "",
                tint = Color.Gray
            )

        }
    }
}

@Preview
@Composable
fun preview() {
    PinItem()
}