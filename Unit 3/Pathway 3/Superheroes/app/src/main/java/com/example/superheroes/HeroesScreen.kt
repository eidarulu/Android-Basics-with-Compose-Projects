package com.example.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_item_space)),
        contentPadding = contentPadding
    ) {
        items(heroes) { hero ->
            HeroListItem(hero = hero)
        }
    }
}

@Composable
fun HeroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clip(MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding))
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding))
                    .clip(shape = MaterialTheme.shapes.small)
                    .size(dimensionResource(id = R.dimen.image_size))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeroListItem() {
    SuperheroesTheme {
        HeroListItem(
            hero = Hero(
                nameRes = R.string.hero1,
                descriptionRes = R.string.description1,
                imageRes = R.drawable.android_superhero1
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeroesList() {
    SuperheroesTheme {
        HeroesList(HeroesRepository.heroes)
    }
}