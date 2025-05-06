package ru.liiceberg.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.liiceberg.presentation.utils.ResourceManager
import ru.liiceberg.presentation.utils.ResourceManagerImpl

@Module
@InstallIn(SingletonComponent::class)
interface PresentationModule {

    @Binds
    fun bindResourceManagerToResourceManagerImpl(impl: ResourceManagerImpl): ResourceManager

}