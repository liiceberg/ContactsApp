package ru.liiceberg.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.liiceberg.domain.usecase.GetAllContactsUseCase
import ru.liiceberg.domain.usecase.MakeCallUseCase
import ru.liiceberg.domain.usecase.impl.GetAllContactsUseCaseImpl
import ru.liiceberg.domain.usecase.impl.MakeCallUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindGetAllContactsUseCaseToGetAllContactsUseCaseImpl(impl: GetAllContactsUseCaseImpl) : GetAllContactsUseCase

    @Binds
    fun bindMakeCallUseCaseToMakeCallUseCaseImpl(impl: MakeCallUseCaseImpl) : MakeCallUseCase
}