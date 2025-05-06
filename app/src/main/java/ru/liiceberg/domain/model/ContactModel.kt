package ru.liiceberg.domain.model

data class ContactModel(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val photoUri: String,
)