package ru.liiceberg.domain.mapper

import ru.liiceberg.data.model.Contact
import ru.liiceberg.domain.model.ContactModel
import javax.inject.Inject

class ContactModelMapper @Inject constructor() {

    fun mapContactToContactModel(contact: Contact) : ContactModel {
        return with(contact) {
            ContactModel(id, name ?: "", phoneNumber ?: "", photoUri ?: "")
        }
    }

}