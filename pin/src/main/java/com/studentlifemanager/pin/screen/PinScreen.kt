package com.studentlifemanager.pin.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.studentlifemanager.common.R
import com.studentlifemanager.pin.screen.PinViewModel.PinUiState.Success
import com.studentlifemanager.pin.utility.CustomDialog

/**
 * Compose screen for pin item listing
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PinScreen(
    pinViewModel: PinViewModel = hiltViewModel()
) {
    // remember dialog state
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CustomDialog(pinViewModel, value = "", setShowDialog = { showDialog.value = it }) {
            Log.i("HomePage", "HomePage : $it")
        }
    }

    Scaffold(floatingActionButton = {

        // floating action button to open dialog in order to add pin
        FloatingActionButton(
            onClick = { showDialog.value = true },
            shape = CircleShape,
            modifier = Modifier.padding(bottom = 10.dp, end = 10.dp),
            containerColor = colorResource(id = R.color.colorPrimary),
            contentColor = colorResource(id = R.color.white)
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }
    }, content = {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            // access local data from database
            pinViewModel.getPinData()
            // observe response as state
            val uiState by pinViewModel.uiState.collectAsState()
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                when (uiState) {
                    is Success -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items((uiState as Success).pinList) {
                                PinItem(it)
                            }
                        }
                    }

                    is Error -> {
                        // pending
                    }

                    else -> {}
                }

            }

        }
    })
}

@Preview
@Composable
fun PreviewA() {
    PinScreen()
}