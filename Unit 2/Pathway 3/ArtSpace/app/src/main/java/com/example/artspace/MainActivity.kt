package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtBoard(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AppPreview() {
    ArtSpaceTheme {
        ArtBoard(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        )
    }
}

@Composable
fun ArtBoard(
    modifier: Modifier = Modifier
) {
    var image by remember { mutableIntStateOf(R.drawable.green_wall) }
    var title by remember { mutableIntStateOf(R.string.green_wall_descriptor) }
    var date by remember { mutableIntStateOf(R.string.green_wall_date) }

    Column(
        modifier = modifier.padding(top = 32.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(image)
        ArtworkDescriptor(title, date)
        DisplayController(
            onPrevious = {
                when (image) {
                    R.drawable.sky -> {
                        image = R.drawable.mountains
                        title = R.string.mountains_descriptor
                        date = R.string.mountains_date
                    }
                    R.drawable.green_wall -> {
                        image = R.drawable.sky
                        title = R.string.sky_descriptor
                        date = R.string.sky_date
                    }
                    R.drawable.mountains -> {
                        image = R.drawable.green_wall
                        title = R.string.green_wall_descriptor
                        date = R.string.green_wall_date
                    }
                }
            },
            onNext = {
                when (image) {
                    R.drawable.sky -> {
                        image = R.drawable.green_wall
                        title = R.string.green_wall_descriptor
                        date = R.string.green_wall_date
                    }
                    R.drawable.green_wall -> {
                        image = R.drawable.mountains
                        title = R.string.mountains_descriptor
                        date = R.string.mountains_date
                    }
                    R.drawable.mountains -> {
                        image = R.drawable.sky
                        title = R.string.sky_descriptor
                        date = R.string.sky_date
                    }
                }
            }
        )
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes image: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center)
            .padding(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        shape = RectangleShape
    ) {
        Image(
            painter = painterResource(image),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    @StringRes title: Int,
    @StringRes date: Int
) {
    Column(
        modifier = Modifier
            .padding(32.dp)
            .background(color = MaterialTheme.colorScheme.surfaceContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(title),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.author),
                modifier = Modifier.padding(end = 16.dp),
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(date),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun DisplayController(
    onPrevious: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            modifier = Modifier.width(150.dp),
            onClick = onPrevious
        ) {
            Text(text = stringResource(id = R.string.previous_button))
        }
        Button(
            modifier = Modifier.width(150.dp),
            onClick = onNext
        ) {
            Text(text = stringResource(id = R.string.next_button))
        }
    }
}