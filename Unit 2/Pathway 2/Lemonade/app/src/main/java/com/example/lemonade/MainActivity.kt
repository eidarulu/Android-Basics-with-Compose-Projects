package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Lemonade",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var step by remember { mutableIntStateOf(1) }
    var squeeze by remember { mutableIntStateOf(0) }

    when(step) {
        1 -> LemonView(
            imageId = R.drawable.lemon_tree,
            textId = R.string.lemon_tree,
            descId = R.string.description_lemon_tree,
            onImageClick = {
                step++
                squeeze = (2..4).random()
            }
        )
        2 -> LemonView(
            imageId = R.drawable.lemon_squeeze,
            textId = R.string.lemon_squeeze,
            descId = R.string.description_lemon,
            onImageClick = {
                squeeze--
                if (squeeze == 0) step = 3
            }
        )
        3 -> LemonView(
            imageId = R.drawable.lemon_drink,
            textId = R.string.lemonade_drink,
            descId = R.string.description_glass_of_lemonade,
            onImageClick = { step = 4 }
        )
        4 -> LemonView(
            imageId = R.drawable.lemon_restart,
            textId = R.string.empty_glass,
            descId = R.string.description_empty_glass,
            onImageClick = { step = 1 }
        )
    }
}

@Composable
fun LemonView(
    imageId: Int,
    textId: Int,
    descId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults
                .buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(descId),
                modifier = Modifier
                    .width(128.dp)
                    .height(160.dp)
                    .padding(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(textId),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeAppPreview() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LemonadeApp(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}