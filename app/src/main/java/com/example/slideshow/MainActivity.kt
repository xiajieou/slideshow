package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshow.ui.theme.SlideshowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SlideshowTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    SlideshowApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SlideshowApp(modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    val captions = listOf(
        R.string.team1,
        R.string.team2,
        R.string.team3,
        R.string.team4,
        R.string.team5
    )

    var currentImage by remember { mutableStateOf(0) }
    var pictureInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(images[currentImage]),
            contentDescription = stringResource(captions[currentImage]),
            modifier = Modifier.size(200.dp)
        )

        Text(
            text = stringResource(captions[currentImage]),
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentImage == 0) {
                        currentImage = images.size - 1
                    } else {
                        currentImage--
                    }
                }
            ) {
                Text(text = stringResource(R.string.back))
            }

            Button(
                onClick = {
                    if (currentImage == images.size - 1) {
                        currentImage = 0
                    } else {
                        currentImage++
                    }
                }
            ) {
                Text(text = stringResource(R.string.next))
            }
        }

        TextField(
            value = pictureInput,
            onValueChange = { pictureInput = it },
            label = {
                Text(text = stringResource(R.string.picture_number))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )

        Button(
            onClick = {
                val pictureNumber = pictureInput.toIntOrNull() ?: 0

                if (pictureNumber >= 1 && pictureNumber <= images.size) {
                    currentImage = pictureNumber - 1
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.go))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideshowTheme {
        SlideshowApp()
    }
}