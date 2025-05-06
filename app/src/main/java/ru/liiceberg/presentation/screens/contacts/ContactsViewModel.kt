package ru.liiceberg.presentation.screens.contacts

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.liiceberg.R
import ru.liiceberg.domain.usecase.GetAllContactsUseCase
import ru.liiceberg.presentation.base.BaseViewModel
import ru.liiceberg.presentation.model.LoadState
import ru.liiceberg.presentation.utils.ResourceManager
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val mapper: ContactUiModelMapper,
    private val resourceManager: ResourceManager,
) : BaseViewModel<ContactsState, ContactsEvent, ContactsAction>(ContactsState()) {

    override fun obtainEvent(event: ContactsEvent) {
        when (event) {
            is ContactsEvent.LoadContacts -> init()
        }
    }

    override fun init() {
        viewModelScope.launch {
            runCatching {
                Log.d("LOAD STATE", "LOAD")
                viewState = viewState.copy(loadState = LoadState.Loading)
                getAllContactsUseCase.invoke()
            }.onSuccess { list ->
                Log.d("LOAD STATE", "SUC")
                val groupedContacts = mutableMapOf<Char, MutableList<ContactUiModel>>()
                list.forEach {
                    val letter = it.name.firstOrNull()?.uppercaseChar()
                    val key = if (letter != null && letter.isLetter()) letter else '#'
                    groupedContacts
                        .getOrPut(key) { mutableListOf() }
                        .add(
                            mapper.mapContactModelToContactUiModel(it)
                        )
                }
                viewState = viewState.copy(
                    contacts = groupedContacts.toSortedMap { a, b ->
                        when {
                            a == b -> 0
                            a == '#' -> 1
                            b == '#' -> -1
                            else -> a.compareTo(b)
                        }
                    },
                    loadState = LoadState.Success
                )

            }.onFailure { ex ->
                Log.d("LOAD STATE", "FAIL")
                val msg = ex.message ?: resourceManager.getString(R.string.common_error_general_message)
                viewState = viewState.copy(loadState = LoadState.Error(msg))
            }
        }
    }
}