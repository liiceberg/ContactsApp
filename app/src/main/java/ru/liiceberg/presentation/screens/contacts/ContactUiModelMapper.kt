package ru.liiceberg.presentation.screens.contacts

import ru.liiceberg.domain.model.ContactModel
import javax.inject.Inject

class ContactUiModelMapper @Inject constructor() {
    fun mapContactModelToContactUiModel(contactModel: ContactModel) : ContactUiModel {
        return with(contactModel) {
            ContactUiModel(id, name, phoneNumber, photoUri)
        }
    }
}