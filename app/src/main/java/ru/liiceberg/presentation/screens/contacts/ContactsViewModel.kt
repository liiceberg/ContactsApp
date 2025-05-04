package ru.liiceberg.presentation.screens.contacts

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.liiceberg.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
) : BaseViewModel<ContactsState, ContactsEvent, ContactsAction>(ContactsState()) {

}