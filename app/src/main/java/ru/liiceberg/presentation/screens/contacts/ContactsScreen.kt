package ru.liiceberg.presentation.screens.contacts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactsViewModel = hiltViewModel(),
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    ContactsScreen(state)
}

@Composable
private fun ContactsScreen(
    state: ContactsState,
//    onContactClick: () -> Unit,
) {
    LazyColumn {
        items(state.contacts.size) {
            ContactItem()
        }
    }
}

@Composable
fun ContactItem() {

}

@Preview
@Composable
private fun ContactsViewPreview() {
    ContactsScreen(ContactsState())
}