package ru.liiceberg.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.liiceberg.R
import ru.liiceberg.presentation.ui.theme.ContactsAppTheme

@Composable
fun BaseCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    image: @Composable () -> Unit = {},
) {
    Card(modifier.fillMaxWidth()) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            image()
            Column(Modifier.padding(start = 24.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(text = description, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview
@Composable
private fun CardsPreview() {
    ContactsAppTheme {
        Column {
            BaseCard("Title", "Description", image = {
                Image(
                    painterResource(R.drawable.person), null,
                    Modifier.height(48.dp)
                )
            })
        }
    }
}