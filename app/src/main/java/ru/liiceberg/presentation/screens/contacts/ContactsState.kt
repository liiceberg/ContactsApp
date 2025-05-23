package ru.liiceberg.presentation.screens.contacts

import androidx.compose.runtime.Immutable
import ru.liiceberg.presentation.model.UiState
import ru.liiceberg.presentation.model.LoadState

@Immutable
data class ContactsState(
    val loadState: LoadState = LoadState.Initial,
    val contacts: Map<Char, List<ContactUiModel>> = emptyMap<Char, List<ContactUiModel>>(),
) : UiState