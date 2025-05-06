package ru.liiceberg.domain.usecase

import ru.liiceberg.domain.model.ContactModel

interface GetAllContactsUseCase {
    suspend fun invoke() : List<ContactModel>
}