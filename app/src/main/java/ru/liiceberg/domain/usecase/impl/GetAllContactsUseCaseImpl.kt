package ru.liiceberg.domain.usecase.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.liiceberg.domain.mapper.ContactModelMapper
import ru.liiceberg.domain.model.ContactModel
import ru.liiceberg.domain.usecase.GetAllContactsUseCase
import ru.liiceberg.domain.repository.ContactsRepository
import javax.inject.Inject

class GetAllContactsUseCaseImpl @Inject constructor(
    private val repository: ContactsRepository,
    private val dispatcher: CoroutineDispatcher,
    private val mapper: ContactModelMapper,
) : GetAllContactsUseCase {

    override suspend operator fun invoke(): List<ContactModel> {
        return withContext(dispatcher) {
            repository.getAll().map { mapper.mapContactToContactModel(it) }
        }
    }

}