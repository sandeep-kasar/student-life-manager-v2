package com.studentlifemanager.pin.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.studentlifemanager.pin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Website",
                        modifier = Modifier
                            .border(
                                BorderStroke(1.dp, colorResource(id = R.color.colorPrimaryDark))
                            )
                            .padding(10.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        color = colorResource(id = R.color.colorPrimaryDark),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Video",
                        modifier = Modifier
                            .border(
                                BorderStroke(1.dp, colorResource(id = R.color.dark_gray))
                            )
                            .padding(10.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        color = colorResource(id = R.color.extra_dark_gray),
                        textAlign = TextAlign.Center
                    )
                }
                var title by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(top = 10.dp),
                    value = title,
                    onValueChange = { title = it },
                    placeholder = {
                        Text(
                            text = "Title",
                            color = Color.Gray,
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray,
                        containerColor = Color.White,
                    )
                )

                var subject by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(top = 10.dp),
                    value = subject,
                    onValueChange = { subject = it },
                    placeholder = {
                        Text(
                            text = "Subject",
                            color = Color.Gray,
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray,
                        containerColor = Color.White,
                    )
                )
                var link by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(top = 10.dp),
                    value = link,
                    onValueChange = { link = it },
                    placeholder = {
                        Text(
                            text = "https://www.dictionary.com/browse/example",
                            color = Color.Gray,
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray,
                        containerColor = Color.White,
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {},
                        Modifier.padding(end = 35.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.colorPrimaryDark)
                            ),
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Text(
                        text = "Ok",
                        modifier = Modifier.padding(end = 25.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.colorPrimaryDark)
                        ),
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    CustomDialog(value = "aaa", setShowDialog = { true }, setValue = { true })
}
