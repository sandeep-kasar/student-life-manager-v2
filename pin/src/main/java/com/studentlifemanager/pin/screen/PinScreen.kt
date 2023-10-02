package com.studentlifemanager.pin.screen

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studentlifemanager.pin.R

@Composable
fun PinScreen() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
        ) {
            FloatingActionButton(
                onClick = { },
                shape = CircleShape,
                modifier = Modifier.padding(bottom = 10.dp, end = 10.dp),
                containerColor = colorResource(id = R.color.colorPrimary),
                contentColor = colorResource(id = R.color.white)
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }

    }
}

@Preview
@Composable
fun PreviewA() {
    PinScreen()
}