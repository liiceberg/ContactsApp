package ru.liiceberg.presentation.screens.contacts

import ru.liiceberg.presentation.model.UiEvent

sealed class ContactsEvent : UiEvent {
    data object LoadContacts : ContactsEvent()
    data class ContactClicked(val id: Long) : ContactsEvent()
}