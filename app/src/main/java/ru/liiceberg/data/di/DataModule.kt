package ru.liiceberg.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.liiceberg.data.repository.ContactsRepositoryImpl
import ru.liiceberg.domain.repository.ContactsRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindContactsRepositoryToContactsRepositoryImpl(impl: ContactsRepositoryImpl) : ContactsRepository
}