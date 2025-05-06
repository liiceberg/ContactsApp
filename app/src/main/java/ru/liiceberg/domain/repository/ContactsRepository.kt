package ru.liiceberg.domain.repository

import ru.liiceberg.data.model.Contact

interface ContactsRepository {
    fun getAll() : List<Contact>
}