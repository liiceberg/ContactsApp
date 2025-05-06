package ru.liiceberg.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.liiceberg.domain.usecase.GetAllContactsUseCase
import ru.liiceberg.domain.usecase.impl.GetAllContactsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindGetAllContactsUseCaseToGetAllContactsUseCaseImpl(impl: GetAllContactsUseCaseImpl) : GetAllContactsUseCase

}