package com.studentlifemanager.pin.utility

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studentlifemanager.common.data.entity.PinEntity
import com.studentlifemanager.common.R
import com.studentlifemanager.pin.screen.PinViewModel
import com.studentlifemanager.pin.utility.Constant.PIN_VIDEO
import com.studentlifemanager.pin.utility.Constant.PIN_WEB
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    pinViewModel: PinViewModel,
    value: String,
    setShowDialog: (Boolean) -> Unit,
    setValue: (String) -> Unit
) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = Color.White
        ) {
            val selectedWeb = remember { mutableStateOf(true) }
            val selectedVideo = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(
                        onClick = {
                            selectedWeb.value = true
                            if (selectedWeb.value) {
                                selectedWeb.value = true
                                selectedVideo.value = false
                            } else {
                                selectedVideo.value = true
                                selectedWeb.value = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),  //avoid the oval shape
                        shape = RectangleShape,
                        border = if (selectedWeb.value) BorderStroke(
                            1.dp,
                            colorResource(id = R.color.colorPrimaryDark)
                        ) else BorderStroke(1.dp, colorResource(id = R.color.dark_gray)),
                    ) {
                        Text(
                            text = stringResource(id = R.string.pin_type_web),
                            color = if (selectedWeb.value) colorResource(id = R.color.colorPrimaryDark) else colorResource(
                                id = R.color.extra_dark_gray
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedButton(
                        onClick = {
                            selectedVideo.value = true
                            if (selectedVideo.value) {
                                selectedVideo.value = true
                                selectedWeb.value = false
                            } else {
                                selectedWeb.value = true
                                selectedVideo.value = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),  //avoid the oval shape
                        shape = RectangleShape,
                        border = if (selectedVideo.value) BorderStroke(
                            1.dp,
                            colorResource(id = R.color.colorPrimaryDark)
                        ) else BorderStroke(1.dp, colorResource(id = R.color.dark_gray)),
                    ) {
                        Text(
                            text = stringResource(id = R.string.pin_type_video),
                            color = if (selectedVideo.value) colorResource(id = R.color.colorPrimaryDark) else colorResource(
                                id = R.color.extra_dark_gray
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                var title by remember { mutableStateOf("") }
                val isEmpty = rememberSaveable { mutableStateOf(false) }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(top = 10.dp),
                    value = title,
                    onValueChange = { title = it },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.pin_dia_title),
                            color = Color.Gray,
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray,
                        containerColor = Color.White,
                    ),
                    supportingText = {
                        if (isEmpty.value && title.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.pin_dia_validate_title),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
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
                            text = stringResource(id = R.string.pin_dia_subject),
                            color = Color.Gray,
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray,
                        containerColor = Color.White,
                    ),
                    supportingText = {
                        if (isEmpty.value && subject.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.pin_dia_validate_subject),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                var link by remember { mutableStateOf("") }
                val isValidLink = Patterns.WEB_URL.matcher(link).matches()
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(top = 10.dp),
                    value = link,
                    onValueChange = { link = it },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.pin_dia_link),
                            color = Color.Gray,
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Gray,
                        focusedBorderColor = Color.Gray,
                        containerColor = Color.White,
                    ),
                    supportingText = {
                        if (isEmpty.value && (link.isEmpty() || !isValidLink)) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.pin_dia_validate_link),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { setShowDialog(false) },
                        Modifier.padding(end = 35.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.pin_dia_cancel),
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.colorPrimaryDark)
                            ),
                            fontWeight = FontWeight.Normal
                        )
                    }
                    TextButton(
                        onClick = {
                            if (title.isEmpty() || subject.isEmpty() || link.isEmpty() || !isValidLink) {
                                isEmpty.value = true
                            } else {
                                val pinType = if (selectedWeb.value) PIN_WEB else PIN_VIDEO
                                pinViewModel.insert(
                                    PinEntity(
                                        pnTitle = title,
                                        pnSubject = subject,
                                        pnLink = link,
                                        pnLinkType = pinType,
                                        pnDate = Date().toString()
                                    )
                                )
                                setShowDialog(false)
                            }
                        },
                        Modifier.padding(end = 25.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.pin_dia_ok),
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
}

@Preview
@Composable
fun Preview() {
    CustomDialog(
        pinViewModel = viewModel(),
        value = "aaa",
        setShowDialog = { },
        setValue = { })
}
