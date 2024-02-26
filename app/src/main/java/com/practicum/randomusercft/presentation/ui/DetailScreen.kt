package com.practicum.randomusercft.presentation.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.practicum.randomusercft.R
import com.practicum.randomusercft.data.models.UsersModel
import com.practicum.randomusercft.presentation.theme.RandomUserCFTTheme
import java.util.Locale

@Composable
fun DetailScreen(
    user: UsersModel
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = user.picture,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_launcher_foreground)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = user.fullName)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = user.country)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = user.email,
            modifier = Modifier.clickable {
                getEmail(
                    context,
                    user.email,
                    "Hello! ${user.fullName}",
                    "Hello! ${user.fullName}"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = user.phone,
            modifier = Modifier.clickable {
                makeCall(
                    context,
                    user.phone
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = user.coordinates,
            modifier = Modifier.clickable {
                getCoordinates(
                    context,
                    user.coordinates
                )
            }
        )
    }
}


private fun getEmail(context: Context, address: String, subject: String, message: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_EMAIL, address)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, message)
    }
    startActivity(context, createChooser(intent, "Send an email..."), null)
}

private fun makeCall(context: Context, mob: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$mob")
        context.startActivity(intent)
    } catch (e: java.lang.Exception) {
        Toast.makeText(
            context,
            "Unable to call at this time", Toast.LENGTH_SHORT
        ).show()
    }
}

private fun getCoordinates(context: Context, coordinates: String) {
    val uri = "http://maps.google.co.in/maps?q=$coordinates"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(intent)
}

@Preview
@Composable
fun DetailsScreenPreview() {
    RandomUserCFTTheme {
        DetailScreen(
            user = UsersModel(
                "Sarah Cote",
                "Russia Moscow",
                "example@mail.ru",
                "",
                "1234",
                ""
            )
        )
    }
}