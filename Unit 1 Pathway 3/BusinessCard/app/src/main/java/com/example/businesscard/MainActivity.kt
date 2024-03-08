package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xEF6A3FA1))
            .padding(16.dp),
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            IdentityInformation(
                imagePainter = painterResource(id = R.drawable.android_logo),
                fullName = stringResource(id = R.string.full_name),
                title = stringResource(id = R.string.title)
            )
        }

        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 16.dp)
        ) {
            ContactInformation()
        }
    }
}

@Composable
private fun IdentityInformation(
    imagePainter: Painter,
    fullName: String,
    title: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .height(128.dp)
                .background(Color(0xF1420235))
        )

        Text(
            text = fullName,
            fontFamily = FontFamily.Cursive,
            fontSize = 48.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color.White
        )

        Text(
            text = title,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            color = Color(0xF160D62D)
        )
    }
}

@Composable
private fun ContactInformation() {
    Column {
        RowContactInfo(
            icon = Icons.Rounded.Phone,
            iconDescription = stringResource(id = R.string.icon_description_1),
            contact = stringResource(id = R.string.contact_info_1)
        )
        
        RowContactInfo(
            icon = Icons.Rounded.Share,
            iconDescription = stringResource(id = R.string.icon_description_2),
            contact = stringResource(id = R.string.contact_info_2)
        )
        
        RowContactInfo(
            icon = Icons.Rounded.Email,
            iconDescription = stringResource(id = R.string.icon_description_3),
            contact = stringResource(id = R.string.contact_info_3)
        )
    }
}

@Composable
private fun RowContactInfo(
    icon: ImageVector,
    iconDescription: String,
    contact: String,
    modifier: Modifier = Modifier
) {
    Row(modifier.padding(4.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            tint = Color(0xF160D62D)
        )
        
        Text(
            text = contact,
            modifier = Modifier.padding(start = 20.dp),
            color = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardAppPreview(){
    BusinessCardTheme {
        BusinessCardApp()
    }
}