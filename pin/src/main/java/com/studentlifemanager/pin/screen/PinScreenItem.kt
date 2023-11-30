package com.studentlifemanager.pin.screen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studentlifemanager.common.data.entity.PinEntity
import com.studentlifemanager.common.utils.Constant.DD_MMM_YYYY
import com.studentlifemanager.common.utils.Constant.YYYY_MM_DD
import com.studentlifemanager.common.utils.DateUtil
import com.studentlifemanager.common.R
import java.util.Date

/**
 * pin row composable function
 *
 * @param pinEntity - pin data list
 */
@Composable
fun PinItem(pinEntity: PinEntity) {
    Column(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, color = Color.LightGray)
            )
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .height(40.dp)
                .background(color = colorResource(id = R.color.light_pin_title))
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "T",
                modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                style = TextStyle(
                    fontSize = 17.sp,
                    color = Color.Black
                ),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = pinEntity.pnTitle,
                modifier = Modifier.padding(end = 15.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                fontWeight = FontWeight.Normal
            )
        }
        Row(
            modifier = Modifier
                .height(30.dp)
                .fillMaxSize()
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.List,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                tint = Color.Gray
            )
            Text(
                text = pinEntity.pnSubject,
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
                .heightIn(max = 50.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val context = LocalContext.current
            val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(pinEntity.pnLink)) }
            Icon(
                Icons.Filled.Send,
                contentDescription = "",
                modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                tint = Color.Gray

            )
            ClickableText(
                text = buildAnnotatedString {
                    pushStringAnnotation(tag = "Link", annotation = pinEntity.pnLink)
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.purple_500))) {
                        append(pinEntity.pnLink)
                    }
                },
                modifier = Modifier
                    .padding(end = 15.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray
                ),
                onClick = {
                    val isValidUrl = Patterns.WEB_URL.matcher(pinEntity.pnLink).matches()
                    if (!isValidUrl) {
                        Toast.makeText(
                            context,
                            R.string.pin_dia_validate_link_msg,
                            Toast.LENGTH_LONG
                        ).show()
                        return@ClickableText
                    }
                    try {
                        context.startActivity(intent)
                    } catch (exception: ActivityNotFoundException) {
                        exception.printStackTrace()
                    }
                }
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
                text = DateUtil.formatDateFromString(YYYY_MM_DD, DD_MMM_YYYY, Date().toString()),
                modifier = Modifier.padding(end = 15.dp),
                style = TextStyle(
                    fontSize = 13.sp,
                    color = Color.Gray
                ),
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    PinItem(
        PinEntity(
            1,
            "Math calculus",
            "Mathematics",
            "https://www.dictionary.com/browse/example",
            1,
            "2023-10-24"
        )
    )
}