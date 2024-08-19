package com.example.a30daysofphotography

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30daysofphotography.data.ChallengeRepository
import com.example.a30daysofphotography.model.Challenge
import com.example.a30daysofphotography.ui.theme._30DaysOfPhotographyTheme

@Composable
fun List(
    challenges: List<Challenge>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(challenges) { challenge ->
            ListItem(
                challenge = challenge,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

@Composable
fun ListItem(
    challenge: Challenge,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.clickable { expanded = !expanded },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .width(dimensionResource(id = R.dimen.image_size))
                .animateContentSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            Text(
                text = stringResource(id = challenge.dayIndicatorRes),
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 24.sp
            )
            Text(
                text = stringResource(id = challenge.titleRes),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 36.sp
            )
            Image(
                painter = painterResource(id = challenge.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
                    .size(dimensionResource(R.dimen.image_size))
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            if (expanded) {
                Text(
                    text = stringResource(id = challenge.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp
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
fun ListPreview() {
    _30DaysOfPhotographyTheme {
        List(
            challenges = ChallengeRepository.challenges,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun ListItemPreview() {
    _30DaysOfPhotographyTheme {
        ListItem(
            challenge = ChallengeRepository.challenges.first()
        )
    }
}